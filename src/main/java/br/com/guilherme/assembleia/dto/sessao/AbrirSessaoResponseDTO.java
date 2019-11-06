package br.com.guilherme.assembleia.dto.sessao;

import br.com.guilherme.assembleia.dto.commons.ResponseDTO;
import lombok.*;

/**
 * DTO de response de abertura de uma sessão
 *
 * @author Guilherme Lacerda
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AbrirSessaoResponseDTO extends ResponseDTO {

    private Integer id;

    public AbrirSessaoResponseDTO(Integer id) {
        this.id = id;
    }

    public AbrirSessaoResponseDTO(String mensagemErro) {

        super(mensagemErro);
    }
}
