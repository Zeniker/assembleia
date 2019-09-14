package br.com.guilherme.assembleia.sessao.service;

import br.com.guilherme.assembleia.pauta.service.PautaService;
import br.com.guilherme.assembleia.sessao.dto.AbrirSessaoDTO;
import br.com.guilherme.assembleia.sessao.exceptions.SessaoNaoEncontradaException;
import br.com.guilherme.assembleia.sessao.model.Sessao;
import br.com.guilherme.assembleia.sessao.repository.SessaoRepository;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Service
public class SessaoService {

    private SessaoRepository sessaoRepository;

    private PautaService pautaService;

    public SessaoService(SessaoRepository sessaoRepository, PautaService pautaService) {
        this.sessaoRepository = sessaoRepository;
        this.pautaService = pautaService;
    }


    public Sessao abrirSessao(AbrirSessaoDTO abrirSessaoDTO) {
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
}
