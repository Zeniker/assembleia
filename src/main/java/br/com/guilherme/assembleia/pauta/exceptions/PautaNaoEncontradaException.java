package br.com.guilherme.assembleia.pauta.exceptions;

public class PautaNaoEncontradaException extends RuntimeException {

    public PautaNaoEncontradaException() {
        super("Pauta não encontrada");
    }
}
