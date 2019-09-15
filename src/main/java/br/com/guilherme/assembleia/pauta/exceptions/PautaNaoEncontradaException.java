package br.com.guilherme.assembleia.pauta.exceptions;

/**
 * Exceção a ser utilizada quando a pauta não for encontrada em uma operação.
 *
 * @author Guilherme Lacerda
 */
public class PautaNaoEncontradaException extends RuntimeException {

    public PautaNaoEncontradaException() {
        super("Pauta não encontrada");
    }
}
