package br.com.guilherme.assembleia.voto.dto;

public class RegistrarVotoResponseDTO {

    private Integer id;

    public RegistrarVotoResponseDTO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
