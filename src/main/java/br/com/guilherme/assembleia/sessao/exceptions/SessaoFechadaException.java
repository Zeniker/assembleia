package br.com.guilherme.assembleia.sessao.exceptions;

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
