package br.com.guilherme.assembleia.pauta.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NovaPautaRequestDTO {

    @NotNull
    @NotEmpty
    private String assunto;

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
}
