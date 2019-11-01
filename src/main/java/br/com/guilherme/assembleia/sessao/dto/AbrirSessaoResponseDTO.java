package br.com.guilherme.assembleia.sessao.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * DTO de response de abertura de uma sessão
 *
 * @author Guilherme Lacerda
 */
@Data
public class AbrirSessaoResponseDTO {

    private Integer id;

    public AbrirSessaoResponseDTO(Integer id) {
        this.id = id;
    }

}
