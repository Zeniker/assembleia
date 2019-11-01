package br.com.guilherme.assembleia.sessao.dto;

import br.com.guilherme.assembleia.sessao.model.enums.SituacaoVotacao;
import lombok.*;

/**
 * DTO com o resultado de uma sessão
 *
 * @author Guilherme Lacerda
 */
@Data
public class ResultadoSessaoDTO {

    private Integer totalVotos;
    private Integer totalVotosAFavor;
    private Integer totalVotosContra;
    private SituacaoVotacao situacao;

}
