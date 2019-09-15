package br.com.guilherme.assembleia.voto.dto;

/**
 * DTO de response contendo o status de um cpf
 *
 * @author Guilherme Lacerda
 */
public class CPFStatusDTO {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
