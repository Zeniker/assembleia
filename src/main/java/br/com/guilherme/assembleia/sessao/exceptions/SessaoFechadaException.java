package br.com.guilherme.assembleia.sessao.exceptions;

public class SessaoFechadaException extends RuntimeException{

    public SessaoFechadaException() {
        super("A sessão informada está fechada");
    }
}
