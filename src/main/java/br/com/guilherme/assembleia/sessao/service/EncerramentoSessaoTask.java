package br.com.guilherme.assembleia.sessao.service;

import java.util.TimerTask;

/**
 * Task utilizada para encerrar uma sessão
 *
 * @author Guilherme Lacerda
 */
public class EncerramentoSessaoTask extends TimerTask {

    private final SessaoService sessaoService;
    private final Integer idSessao;

    EncerramentoSessaoTask(SessaoService sessaoService, Integer idSessao) {

        this.sessaoService = sessaoService;
        this.idSessao = idSessao;
    }

    @Override
    public void run() {
        sessaoService.fecharSessao(idSessao);
    }
}
