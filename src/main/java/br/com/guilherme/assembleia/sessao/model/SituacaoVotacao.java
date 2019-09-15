package br.com.guilherme.assembleia.sessao.model;

/**
 * Enum com as possíveis situações que uma sessão pode ter
 *
 * @author Guilherme Lacerda
 */
public enum SituacaoVotacao {
    APROVADA, REPROVADA, EMPATE;

    public static SituacaoVotacao getSituacao(Integer votosAFavor, Integer votosContra){
        if (votosAFavor == votosContra) return EMPATE;
        if (votosAFavor > votosContra) return APROVADA;

        return REPROVADA;
    }
}
