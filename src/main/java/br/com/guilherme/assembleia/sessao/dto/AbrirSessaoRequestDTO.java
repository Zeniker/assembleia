package br.com.guilherme.assembleia.sessao.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * DTO de request de abertura de uma sessão
 *
 * @author Guilherme Lacerda
 */
public class AbrirSessaoRequestDTO {

    @NotNull
    private Integer pauta;

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
