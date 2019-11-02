package br.com.guilherme.assembleia.dto.voto;

import br.com.guilherme.assembleia.enums.VotoEscolha;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * DTO de request para registro de voto
 *
 * @author Guilherme Lacerda
 */
@Data
public class RegistrarVotoRequestDTO {

    @NotNull
    private String cpf;

    @NotNull
    private Integer sessaoVotada;

    @NotNull
    private VotoEscolha escolha;

}
