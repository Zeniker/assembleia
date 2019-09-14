package br.com.guilherme.assembleia.sessao.model;

public enum SituacaoVotacao {
    APROVADA, REPROVADA, EMPATE;

    public static SituacaoVotacao getSituacao(Integer votosAFavor, Integer votosContra){
        if (votosAFavor == votosContra) return EMPATE;
        if (votosAFavor > votosContra) return APROVADA;

        return REPROVADA;
    }
}
