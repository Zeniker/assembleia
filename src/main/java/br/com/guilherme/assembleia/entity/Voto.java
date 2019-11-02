package br.com.guilherme.assembleia.entity;

import br.com.guilherme.assembleia.entity.Sessao;
import br.com.guilherme.assembleia.enums.VotoEscolha;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Classe modelo para a tabela Voto no banco de dados
 *
 * @author Guilherme Lacerda
 */
@Data
@Entity
public class Voto {

    @GenericGenerator(name="voto_gen" , strategy="increment")
    @GeneratedValue(generator="voto_gen")
    @Id
    private Integer id;

    @NotNull
    @ManyToOne
    private Sessao sessao;

    @NotNull
    private String cpfAssociado;

    @NotNull
    private VotoEscolha escolha;

}
