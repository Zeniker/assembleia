package br.com.guilherme.assembleia.pauta.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Classe modelo para a tabela Pauta no banco de dados
 *
 * @author Guilherme Lacerda
 */
@Data
@Entity
public class Pauta {

    @GenericGenerator(name="pauta_gen" , strategy="increment")
    @GeneratedValue(generator="pauta_gen")
    @Id
    private Integer id;

    private String assunto;

}
