package br.com.guilherme.assembleia.sessao.service;

import java.util.TimerTask;

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
