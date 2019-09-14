package br.com.guilherme.assembleia.voto.exception;

public class AssociadoJaVotouException extends RuntimeException {

    public AssociadoJaVotouException() {
        super("Associado já votou nesta sessão");
    }
}
