package br.com.guilherme.assembleia.voto.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * DTO de response para registro de voto
 *
 * @author Guilherme Lacerda
 */
@Data
public class RegistrarVotoResponseDTO {

    private Integer id;

    public RegistrarVotoResponseDTO(Integer id) {
        this.id = id;
    }

}
