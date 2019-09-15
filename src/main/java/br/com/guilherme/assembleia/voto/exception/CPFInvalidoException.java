package br.com.guilherme.assembleia.voto.exception;

/**
 * Exceção para ser utilizada quando o cpf do associado for inválido
 *
 * @author Guilherme Lacerda
 */
public class CPFInvalidoException extends RuntimeException {

    public CPFInvalidoException() {
        super("O CPF informado não é válido");
    }
}
