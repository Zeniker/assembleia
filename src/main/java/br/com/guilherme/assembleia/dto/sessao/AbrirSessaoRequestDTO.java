package br.com.guilherme.assembleia.dto.sessao;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * DTO de request de abertura de uma sessão
 *
 * @author Guilherme Lacerda
 */

@Data
public class AbrirSessaoRequestDTO {

    @NotNull
    private Integer pauta;

    @Min(1)
    private Integer duracaoSessao;

}
