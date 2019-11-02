package br.com.guilherme.assembleia.exception;

/**
 * Exceção a ser utilizada quando a sessão não for encontrada durante uma operação
 *
 * @author Guilherme Lacerda
 */
public class SessaoNaoEncontradaException extends RuntimeException {

    public SessaoNaoEncontradaException() {
        super("Sessão não encontrada");
    }
}
