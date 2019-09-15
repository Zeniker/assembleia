package br.com.guilherme.assembleia.sessao.dto;

/**
 * DTO de response de abertura de uma sessão
 *
 * @author Guilherme Lacerda
 */
public class AbrirSessaoResponseDTO {

    private Integer id;

    public AbrirSessaoResponseDTO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
