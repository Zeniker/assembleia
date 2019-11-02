package br.com.guilherme.assembleia.dto.pauta;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * DTO de request para a criação de uma nova pauta
 *
 * @author Guilherme Lacerda
 */
@Data
public class NovaPautaRequestDTO {

    @NotNull
    @NotEmpty
    private String assunto;
}
