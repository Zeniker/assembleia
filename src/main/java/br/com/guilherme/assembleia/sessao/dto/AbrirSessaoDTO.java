package br.com.guilherme.assembleia.sessao.dto;

public class AbrirSessaoDTO {

    private Integer pauta;

    private Integer duracaoSessao;

    public Integer getPauta() {
        return pauta;
    }

    public void setPauta(Integer pauta) {
        this.pauta = pauta;
    }

    public Integer getDuracaoSessao() {
        return duracaoSessao;
    }

    public void setDuracaoSessao(Integer duracaoSessao) {
        this.duracaoSessao = duracaoSessao;
    }
}
