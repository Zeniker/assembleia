package br.com.guilherme.assembleia.sessao.exceptions;

public class SessaoAbertaException extends RuntimeException {

    public SessaoAbertaException() {
        super("Operação não pôde ser concluída. A sessão ainda está em aberto");
    }
}
