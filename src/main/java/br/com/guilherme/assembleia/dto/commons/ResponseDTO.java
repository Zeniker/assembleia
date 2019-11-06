package br.com.guilherme.assembleia.dto.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * DTO de response padrão da API
 *
 * @author Guilherme Lacerda
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private String mensagemErro;
}
