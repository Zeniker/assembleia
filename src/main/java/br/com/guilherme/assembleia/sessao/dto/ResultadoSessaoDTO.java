package br.com.guilherme.assembleia.sessao.dto;

import br.com.guilherme.assembleia.sessao.model.SituacaoVotacao;

/**
 * DTO com o resultado de uma sessão
 *
 * @author Guilherme Lacerda
 */
public class ResultadoSessaoDTO {

    private Integer totalVotos;
    private SituacaoVotacao situacao;

    public Integer getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(Integer totalVotos) {
        this.totalVotos = totalVotos;
    }

    public SituacaoVotacao getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoVotacao situacao) {
        this.situacao = situacao;
    }
}
