package br.com.guilherme.assembleia.voto.repository;


import br.com.guilherme.assembleia.sessao.model.Sessao;
import br.com.guilherme.assembleia.voto.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório de voto para comunicação com BD
 *
 * @author Guilherme Lacerda
 */
public interface VotoRepository extends JpaRepository<Voto, Integer> {

    /**
     * Busca o voto pertencente a um associado na sessão
     *
     * @param cpfAssociado Cpf do associado que votou
     * @return Optional do voto
     */
    Optional<Voto> findByCpfAssociado(String cpfAssociado);

    /**
     * Lista os votos encontrados em uma sessão
     *
     * @param sessao Sessão em que os votos foram registrados
     * @return Lista de votos encontrados
     */
    List<Voto> findBySessao(Sessao sessao);
}
