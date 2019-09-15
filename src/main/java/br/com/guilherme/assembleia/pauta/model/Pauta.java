package br.com.guilherme.assembleia.pauta.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Classe modelo para a tabela Pauta no banco de dados
 *
 * @author Guilherme Lacerda
 */
@Entity
public class Pauta {

    @GenericGenerator(name="pauta_gen" , strategy="increment")
    @GeneratedValue(generator="pauta_gen")
    @Id
    private Integer id;

    private String assunto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
}
