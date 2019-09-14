package br.com.guilherme.assembleia.voto.repository;


import br.com.guilherme.assembleia.voto.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VotoRepository extends JpaRepository<Voto, Integer> {

    Optional<Voto> findByCpfAssociado(String cpfAssociado);
}
