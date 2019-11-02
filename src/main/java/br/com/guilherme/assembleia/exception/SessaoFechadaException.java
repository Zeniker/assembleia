package br.com.guilherme.assembleia.exception;

/**
 * Exceção a ser utilizada quando a sessão estiver fechada durante uma operação
 *
 * @author Guilherme Lacerda
 */
public class SessaoFechadaException extends RuntimeException{

    public SessaoFechadaException() {
        super("A sessão informada está fechada");
    }
}
