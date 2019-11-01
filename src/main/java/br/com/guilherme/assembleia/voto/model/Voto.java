package br.com.guilherme.assembleia.voto.model;

import br.com.guilherme.assembleia.sessao.model.Sessao;
import br.com.guilherme.assembleia.voto.model.enums.VotoEscolha;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public String getCpfAssociado() {
        return cpfAssociado;
    }

    public void setCpfAssociado(String cpfAssociado) {
        this.cpfAssociado = cpfAssociado;
    }

    public VotoEscolha getEscolha() {
        return escolha;
    }

    public void setEscolha(VotoEscolha escolha) {
        this.escolha = escolha;
    }
}
