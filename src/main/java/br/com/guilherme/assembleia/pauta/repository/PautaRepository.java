package br.com.guilherme.assembleia.pauta.repository;

import br.com.guilherme.assembleia.pauta.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta, Integer> {

}
