package br.com.guilherme.assembleia.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Classe modelo para a tabela Sessao no banco de dados
 *
 * @author Guilherme Lacerda
 */
@Data
@Entity
public class Sessao {

    @GenericGenerator(name="sessao_gen" , strategy="increment")
    @GeneratedValue(generator="sessao_gen")
    @Id
    private Integer id;

    @ManyToOne
    @NotNull
    private Pauta pauta;

    @NotNull
    private LocalDateTime dataHoraAbertura;

    @NotNull
    private LocalDateTime dataHoraFechamento;


}
