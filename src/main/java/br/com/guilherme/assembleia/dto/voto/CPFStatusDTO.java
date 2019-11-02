package br.com.guilherme.assembleia.dto.voto;

import lombok.*;

/**
 * DTO de response contendo o status de um cpf
 *
 * @author Guilherme Lacerda
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CPFStatusDTO {

    private String status;

}
