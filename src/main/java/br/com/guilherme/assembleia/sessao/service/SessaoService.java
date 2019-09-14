package br.com.guilherme.assembleia.sessao.service;

import br.com.guilherme.assembleia.pauta.service.PautaService;
import br.com.guilherme.assembleia.sessao.dto.AbrirSessaoRequestDTO;
import br.com.guilherme.assembleia.sessao.dto.ResultadoSessaoDTO;
import br.com.guilherme.assembleia.sessao.exceptions.SessaoNaoEncontradaException;
import br.com.guilherme.assembleia.sessao.model.Sessao;
import br.com.guilherme.assembleia.sessao.model.SituacaoVotacao;
import br.com.guilherme.assembleia.sessao.repository.SessaoRepository;
import br.com.guilherme.assembleia.voto.model.Voto;
import br.com.guilherme.assembleia.voto.model.VotoEscolha;
import br.com.guilherme.assembleia.voto.repository.VotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

@Service
public class SessaoService {

    private SessaoRepository sessaoRepository;

    private PautaService pautaService;

    private VotoRepository votoRepository;

    public SessaoService(SessaoRepository sessaoRepository, PautaService pautaService, VotoRepository votoRepository) {
        this.sessaoRepository = sessaoRepository;
        this.pautaService = pautaService;
        this.votoRepository = votoRepository;
    }


    public Sessao abrirSessao(AbrirSessaoRequestDTO abrirSessaoDTO) {
        Sessao sessao = new Sessao();
        sessao.setPauta(pautaService.buscarPautaPorId(abrirSessaoDTO.getPauta()));
        sessao.setSessaoAberta(true);

        sessao = sessaoRepository.save(sessao);

        if(abrirSessaoDTO.getDuracaoSessao() == null) abrirSessaoDTO.setDuracaoSessao(60);

        agendaFechamentoSessao(sessao, abrirSessaoDTO.getDuracaoSessao());

        return sessao;
    }

    public Sessao buscarSessaoPorId(Integer id) {
        return sessaoRepository.findById(id).orElseThrow(SessaoNaoEncontradaException::new);
    }

    private void agendaFechamentoSessao(final Sessao sessao, Integer segundosParaFechar){
        TimerTask tarefaFechamento = new TimerTask() {
            @Override
            public void run() {
                fecharSessao(sessao.getId());
            }
        };

        Timer timer = new Timer("Fechamento de Sessão");
        timer.schedule(tarefaFechamento, segundosParaFechar * 1000);
    }

    private void fecharSessao(Integer idSessao) {
        Sessao sessao = this.buscarSessaoPorId(idSessao);
        sessao.setSessaoAberta(false);
        sessaoRepository.save(sessao);
    }

    public ResultadoSessaoDTO buscarResultadoSessao(Integer id) {
        Sessao sessao = buscarSessaoPorId(id);

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


        return resultado;
    }
}
