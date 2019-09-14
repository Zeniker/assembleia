package br.com.guilherme.assembleia.sessao.exceptions;

public class SessaoNaoEncontradaException extends RuntimeException {

    public SessaoNaoEncontradaException() {
        super("Sessão não encontrada");
    }
}
