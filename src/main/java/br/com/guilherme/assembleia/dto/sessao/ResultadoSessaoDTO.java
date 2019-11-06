package br.com.guilherme.assembleia.dto.sessao;

import br.com.guilherme.assembleia.dto.commons.ResponseDTO;
import br.com.guilherme.assembleia.enums.SituacaoVotacao;
import lombok.*;

import java.io.Serializable;

/**
 * DTO com o resultado de uma sessão
 *
 * @author Guilherme Lacerda
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ResultadoSessaoDTO extends ResponseDTO {

    private Integer idSessao;
    private Integer totalVotos;
    private Integer totalVotosAFavor;
    private Integer totalVotosContra;
    private SituacaoVotacao situacao;

    public ResultadoSessaoDTO(String mensagemErro) {

        super(mensagemErro);
    }
}
