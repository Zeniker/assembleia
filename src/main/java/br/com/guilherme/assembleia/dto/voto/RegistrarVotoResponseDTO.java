package br.com.guilherme.assembleia.dto.voto;

import br.com.guilherme.assembleia.dto.commons.ResponseDTO;
import lombok.*;

/**
 * DTO de response para registro de voto
 *
 * @author Guilherme Lacerda
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class RegistrarVotoResponseDTO extends ResponseDTO {

    private Integer id;

    public RegistrarVotoResponseDTO(Integer id) {
        this.id = id;
    }

    public RegistrarVotoResponseDTO(String mensagemErro) {

        super(mensagemErro);
    }
}
