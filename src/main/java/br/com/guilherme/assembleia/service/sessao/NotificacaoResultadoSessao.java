package br.com.guilherme.assembleia.service.sessao;

import br.com.guilherme.assembleia.dto.sessao.ResultadoSessaoDTO;
import br.com.guilherme.assembleia.queue.QueueSender;

import java.util.TimerTask;

/**
 * Task utilizada para encerrar uma sessão
 *
 * @author Guilherme Lacerda
 */
public class NotificacaoResultadoSessao extends TimerTask {

    private final SessaoService sessaoService;
    private final Integer idSessao;
    private final QueueSender queueSender;

    NotificacaoResultadoSessao(SessaoService sessaoService, Integer idSessao, QueueSender queueSender) {

        this.sessaoService = sessaoService;
        this.idSessao = idSessao;
        this.queueSender = queueSender;
    }

    @Override
    public void run() {
        ResultadoSessaoDTO resultadoSessaoDTO = sessaoService.buscarResultadoSessao(idSessao);

        queueSender.send("EncerramentoSessao", resultadoSessaoDTO);
    }
}

