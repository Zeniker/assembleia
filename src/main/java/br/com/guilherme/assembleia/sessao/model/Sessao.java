package br.com.guilherme.assembleia.sessao.model;

import br.com.guilherme.assembleia.pauta.model.Pauta;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Classe modelo para a tabela Sessao no banco de dados
 *
 * @author Guilherme Lacerda
 */
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
    private boolean sessaoAberta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public boolean isSessaoAberta() {
        return sessaoAberta;
    }

    public void setSessaoAberta(boolean sessaoAberta) {
        this.sessaoAberta = sessaoAberta;
    }
}
