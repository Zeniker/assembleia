package br.com.guilherme.assembleia.sessao.repository;


import br.com.guilherme.assembleia.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório da sessão para comunicação com BD
 *
 * @author Guilherme Lacerda
 */
public interface SessaoRepository extends JpaRepository<Sessao, Integer> {
}
