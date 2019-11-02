package br.com.guilherme.assembleia.dto.commons;

import br.com.guilherme.assembleia.enums.StatusResposta;
import lombok.Getter;

/**
 * DTO de response padrão da API
 *
 * @param <T> Classe de dados do dto
 *
 * @author Guilherme Lacerda
 */
@Getter
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
}
