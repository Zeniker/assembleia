package br.com.guilherme.assembleia.sessao.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AbrirSessaoRequestDTO {

    @NotNull
    private Integer pauta;

    @NotNull
    @Min(1)
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
