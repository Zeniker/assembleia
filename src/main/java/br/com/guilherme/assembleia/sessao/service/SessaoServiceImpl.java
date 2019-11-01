package br.com.guilherme.assembleia.sessao.service;

import br.com.guilherme.assembleia.pauta.service.PautaServiceImpl;
import br.com.guilherme.assembleia.sessao.dto.AbrirSessaoRequestDTO;
import br.com.guilherme.assembleia.sessao.dto.ResultadoSessaoDTO;
import br.com.guilherme.assembleia.sessao.exceptions.SessaoAbertaException;
import br.com.guilherme.assembleia.sessao.exceptions.SessaoNaoEncontradaException;
import br.com.guilherme.assembleia.sessao.model.Sessao;
import br.com.guilherme.assembleia.sessao.model.SituacaoVotacao;
import br.com.guilherme.assembleia.sessao.repository.SessaoRepository;
import br.com.guilherme.assembleia.voto.model.Voto;
import br.com.guilherme.assembleia.voto.model.VotoEscolha;
import br.com.guilherme.assembleia.voto.repository.VotoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

    private VotoRepository votoRepository;

    public SessaoServiceImpl(SessaoRepository sessaoRepository, PautaServiceImpl pautaService, VotoRepository votoRepository) {
        this.sessaoRepository = sessaoRepository;
        this.pautaService = pautaService;
        this.votoRepository = votoRepository;
    }


    /**
     * Abre uma nova sessão de votação no sistema
     *
     * @param abrirSessaoDTO Objeto com as informações que serão utilizadas na sessão
     * @return Sessão aberta
     */
    public Sessao abrirSessao(AbrirSessaoRequestDTO abrirSessaoDTO) {
        LocalDateTime dataHoraAbertura = LocalDateTime.now();

        Sessao sessao = new Sessao();
        sessao.setPauta(pautaService.buscarPautaPorId(abrirSessaoDTO.getPauta()));
        sessao.setDataHoraAbertura(dataHoraAbertura);
        sessao.setDataHoraFechamento(calculaDataHoraFechamento(dataHoraAbertura, abrirSessaoDTO.getDuracaoSessao()));

        sessao = sessaoRepository.save(sessao);

        return sessao;
    }

    private LocalDateTime calculaDataHoraFechamento(LocalDateTime dataHoraAbertura, Integer duracao){
        if(duracao == null) duracao = 60;

        return dataHoraAbertura.plusSeconds(duracao);
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
    public ResultadoSessaoDTO buscarResultadoSessao(Integer id) {
        Sessao sessao = buscarSessaoPorId(id);
        if (isSessaoAberta(sessao)) throw new SessaoAbertaException();

        List<Voto> votos = votoRepository.findBySessao(sessao);

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
        resultado.setSituacao(SituacaoVotacao.getSituacao(totalAFavor, totalContra));
        resultado.setTotalVotosAFavor(totalAFavor);
        resultado.setTotalVotosContra(totalContra);

        return resultado;
    }
}
