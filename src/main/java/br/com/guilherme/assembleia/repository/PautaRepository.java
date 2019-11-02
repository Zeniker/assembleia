package br.com.guilherme.assembleia.repository;

import br.com.guilherme.assembleia.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório da pauta para comunicação com BD
 *
 * @author Guilherme Lacerda
 */
public interface PautaRepository extends JpaRepository<Pauta, Integer> {

}
