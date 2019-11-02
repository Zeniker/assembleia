package br.com.guilherme.assembleia.service.voto;

import br.com.guilherme.assembleia.dto.voto.RegistrarVotoRequestDTO;
import br.com.guilherme.assembleia.voto.model.Voto;

import java.util.List;

public interface VotoService {

    /**
     * Registra um novo voto para uma sessão em aberto
     *
     * @param registrarVotoDTO Objeto com as informações que serão utilizadas no voto
     * @return Voto registrado
     */
    Voto registrarVoto(RegistrarVotoRequestDTO registrarVotoDTO);

    /**
     * Busca os votos realizados em uma sessão
     *
     * @param idSessao Utilizado para encontrar a sessão dos votos
     * @return Lista de votos encontrados
     */
    List<Voto> buscarVotosDaSessao(Integer idSessao);
}
