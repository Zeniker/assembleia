package br.com.guilherme.assembleia.voto.exception;

/**
 * Exceção para ser utilizada quando o associado não for elegível para votar
 *
 * @author Guilherme Lacerda
 */
public class AssociadoNaoElegivelException extends RuntimeException {

    public AssociadoNaoElegivelException() {
        super("Associado não está elegível para voto.");
    }
}
