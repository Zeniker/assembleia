package br.com.guilherme.assembleia.voto.dto;

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
