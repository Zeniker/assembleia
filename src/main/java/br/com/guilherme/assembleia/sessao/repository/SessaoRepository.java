package br.com.guilherme.assembleia.sessao.repository;


import br.com.guilherme.assembleia.sessao.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoRepository extends JpaRepository<Sessao, Integer> {
}
