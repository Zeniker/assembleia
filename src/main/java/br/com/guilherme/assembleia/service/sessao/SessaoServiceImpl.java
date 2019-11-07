package br.com.guilherme.assembleia.service.sessao;

import br.com.guilherme.assembleia.queue.QueueSender;
import br.com.guilherme.assembleia.service.pauta.PautaServiceImpl;
import br.com.guilherme.assembleia.dto.sessao.AbrirSessaoRequestDTO;
import br.com.guilherme.assembleia.dto.sessao.ResultadoSessaoDTO;
import br.com.guilherme.assembleia.exception.SessaoAbertaException;
import br.com.guilherme.assembleia.exception.SessaoNaoEncontradaException;
import br.com.guilherme.assembleia.entity.Sessao;
import br.com.guilherme.assembleia.enums.SituacaoVotacao;
import br.com.guilherme.assembleia.repository.SessaoRepository;
import br.com.guilherme.assembleia.entity.Voto;
import br.com.guilherme.assembleia.enums.VotoEscolha;
import br.com.guilherme.assembleia.repository.VotoRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

/**
 * Classe de serviço com as funções de sessão
 *
 * @author Guilherme Lacerda
 */
@Service
public class SessaoServiceImpl implements SessaoService{

    private SessaoRepository sessaoRepository;

    private PautaServiceImpl pautaService;

    private QueueSender queueSender;

    public SessaoServiceImpl(SessaoRepository sessaoRepository, PautaServiceImpl pautaService, VotoRepository votoRepository,
                             QueueSender queueSender) {

        this.sessaoRepository = sessaoRepository;
        this.pautaService = pautaService;
        this.queueSender = queueSender;
    }


    /**
     * Abre uma nova sessão de votação no sistema
     *
     * @param abrirSessaoDTO Objeto com as informações que serão utilizadas na sessão
     * @return Sessão aberta
     */
    public Sessao abrirSessao(AbrirSessaoRequestDTO abrirSessaoDTO) {
        LocalDateTime dataHoraAbertura = LocalDateTime.now();

        validaDuracaoSessao(abrirSessaoDTO);

        Sessao sessao = new Sessao();
        sessao.setPauta(pautaService.buscarPautaPorId(abrirSessaoDTO.getPauta()));
        sessao.setDataHoraAbertura(dataHoraAbertura);
        sessao.setDataHoraFechamento(calculaDataHoraFechamento(dataHoraAbertura, abrirSessaoDTO.getDuracaoSessao()));

        sessao = sessaoRepository.save(sessao);
        agendaFechamentoSessao(sessao, abrirSessaoDTO.getDuracaoSessao());

        return sessao;
    }

    private void validaDuracaoSessao(AbrirSessaoRequestDTO abrirSessaoRequestDTO){
        if(abrirSessaoRequestDTO.getDuracaoSessao() == null){
            abrirSessaoRequestDTO.setDuracaoSessao(60);
        }
    }

    private LocalDateTime calculaDataHoraFechamento(LocalDateTime dataHoraAbertura, Integer duracao){

        return dataHoraAbertura.plusSeconds(duracao);
    }

    private void agendaFechamentoSessao(final Sessao sessao, Integer segundosParaFechar){

        TimerTask tarefaFechamento = new NotificacaoResultadoSessao(this, sessao.getId(), queueSender);

        Timer timer = new Timer("Notificação de encerramento da sessão");
        timer.schedule(tarefaFechamento, (segundosParaFechar + 2)* 1000);
    }

    /**
     * Busca uma sessão a partir de um id
     *
     * @param id Utilizado para encontrar a sessão
     * @return Sessão encontrada
     */
    public Sessao buscarSessaoPorId(Integer id) {
        return sessaoRepository.findById(id).orElseThrow(SessaoNaoEncontradaException::new);
    }

    /**
     * Valida se uma sessão ainda está aberta para votação
     *
     * @param sessao Sessao para validar
     * @return boolean informando se está aberta
     */
    public boolean isSessaoAberta(Sessao sessao){
        return LocalDateTime.now().isBefore(sessao.getDataHoraFechamento());
    }

    /**
     * Busca o resultado de uma sessão fechada
     *
     * @param id Utilizado para encontrar a sessão e seus dados
     * @return Objeto com a contabilização de votos da sessão e sua situação
     */
    @Transactional(readOnly = true)
    public ResultadoSessaoDTO buscarResultadoSessao(Integer id) {
        Sessao sessao = buscarSessaoPorId(id);
        if (isSessaoAberta(sessao)) throw new SessaoAbertaException();

        Hibernate.initialize(sessao);

        List<Voto> votos = sessao.getVotos();

        Integer totalAFavor = votos.stream()
                .filter(voto -> voto.getEscolha() == VotoEscolha.SIM)
                .collect(Collectors.toList())
                .size();
        Integer totalContra = votos.stream()
                .filter(voto -> voto.getEscolha() == VotoEscolha.NAO)
                .collect(Collectors.toList())
                .size();

        ResultadoSessaoDTO resultado = new ResultadoSessaoDTO();
        resultado.setTotalVotos(votos.size());

        SituacaoVotacao situacaoVotacao = SituacaoVotacao.EMPATE;

        if(totalAFavor > totalContra){
            situacaoVotacao = SituacaoVotacao.APROVADA;
        }else if(totalAFavor < totalContra){
            situacaoVotacao = SituacaoVotacao.REPROVADA;
        }

        resultado.setSituacao(situacaoVotacao);
        resultado.setTotalVotosAFavor(totalAFavor);
        resultado.setTotalVotosContra(totalContra);
        resultado.setIdSessao(id);

        return resultado;
    }
}
