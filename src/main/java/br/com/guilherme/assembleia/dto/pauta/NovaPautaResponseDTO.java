package br.com.guilherme.assembleia.dto.pauta;

import br.com.guilherme.assembleia.dto.commons.ResponseDTO;
import lombok.*;

/**
 * DTO de response da criação de uma nova pauta
 *
 * @author Guilherme Lacerda
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class NovaPautaResponseDTO extends ResponseDTO {

    private Integer id;

    public NovaPautaResponseDTO(String mensagemErro) {

        super(mensagemErro);
    }
}
