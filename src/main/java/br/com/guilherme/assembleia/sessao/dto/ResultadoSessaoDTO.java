package br.com.guilherme.assembleia.sessao.dto;

import br.com.guilherme.assembleia.sessao.model.enums.SituacaoVotacao;

/**
 * DTO com o resultado de uma sessão
 *
 * @author Guilherme Lacerda
 */
public class ResultadoSessaoDTO {

    private Integer totalVotos;
    private Integer totalVotosAFavor;
    private Integer totalVotosContra;
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

    public Integer getTotalVotosAFavor() {
        return totalVotosAFavor;
    }

    public void setTotalVotosAFavor(Integer totalVotosAFavor) {
        this.totalVotosAFavor = totalVotosAFavor;
    }

    public Integer getTotalVotosContra() {
        return totalVotosContra;
    }

    public void setTotalVotosContra(Integer totalVotosContra) {
        this.totalVotosContra = totalVotosContra;
    }
}
