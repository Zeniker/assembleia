package br.com.guilherme.assembleia.commons.dto;

public class ResponseDTO<T> {

    private StatusResposta status;
    private String mensagem;
    private T data;

    public ResponseDTO(String mensagem, T data) {
        this.status = StatusResposta.SUCESSO;
        this.mensagem = mensagem;
        this.data = data;
    }

    public ResponseDTO(Exception exception) {
        this.status = StatusResposta.ERRO;
        this.mensagem = exception.getMessage();
    }

    public StatusResposta getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public T getData() {
        return data;
    }
}