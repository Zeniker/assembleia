package br.com.guilherme.assembleia.exception;

/**
 * Exceção para ser utilizada quando o associado já tem voto registrado na sessão.
 *
 * @author Guilherme Lacerda
 */
public class AssociadoJaVotouException extends RuntimeException {

    public AssociadoJaVotouException() {
        super("Associado já votou nesta sessão");
    }
}
