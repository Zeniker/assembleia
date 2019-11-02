package br.com.guilherme.assembleia.enums;

/**
 * Enum com as possíveis situações que uma sessão pode ter
 *
 * @author Guilherme Lacerda
 */
public enum SituacaoVotacao {
    APROVADA, REPROVADA, EMPATE;

    public static SituacaoVotacao getSituacao(Integer votosAFavor, Integer votosContra){
        if (votosAFavor.equals(votosContra)) return EMPATE;
        if (votosAFavor > votosContra) return APROVADA;

        return REPROVADA;
    }
}
