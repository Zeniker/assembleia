package br.com.guilherme.assembleia.service.pauta;

import br.com.guilherme.assembleia.dto.pauta.NovaPautaRequestDTO;
import br.com.guilherme.assembleia.pauta.model.Pauta;

public interface PautaService {

    /**
     * Cria uma nova pauta no sistema
     *
     * @param novaPautaDTO Objeto com as informações que serão utilizadas na pauta
     * @return Pauta salva
     */
    Pauta criarPauta(NovaPautaRequestDTO novaPautaDTO);

    /**
     * Busca uma pauta a partir de um id
     *
     * @param id Utilizado para encontrar a pauta
     * @return Pauta encontrada
     */
    Pauta buscarPautaPorId(Integer id);
}
