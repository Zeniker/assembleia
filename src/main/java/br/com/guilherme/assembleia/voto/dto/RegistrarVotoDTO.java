package br.com.guilherme.assembleia.voto.dto;

import br.com.guilherme.assembleia.voto.model.VotoEscolha;

public class RegistrarVotoDTO {

    private String cpf;
    private Integer sessaoVotada;
    private VotoEscolha escolha;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getSessaoVotada() {
        return sessaoVotada;
    }

    public void setSessaoVotada(Integer sessaoVotada) {
        this.sessaoVotada = sessaoVotada;
    }

    public VotoEscolha getEscolha() {
        return escolha;
    }

    public void setEscolha(VotoEscolha escolha) {
        this.escolha = escolha;
    }
}
