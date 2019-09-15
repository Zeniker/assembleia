package br.com.guilherme.assembleia.voto.dto;

/**
 * DTO de response para registro de voto
 *
 * @author Guilherme Lacerda
 */
public class RegistrarVotoResponseDTO {

    private Integer id;

    public RegistrarVotoResponseDTO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
