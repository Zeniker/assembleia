package br.com.guilherme.assembleia.sessao.exceptions;

/**
 * Exceção a ser utilizada quando a sessão ainda estiver aberta  durante uma operação
 *
 * @author Guilherme Lacerda
 */
public class SessaoAbertaException extends RuntimeException {

    public SessaoAbertaException() {
        super("Operação não pôde ser concluída. A sessão ainda está em aberto");
    }
}
