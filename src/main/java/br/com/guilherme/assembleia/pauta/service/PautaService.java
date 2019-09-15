package br.com.guilherme.assembleia.pauta.service;

import br.com.guilherme.assembleia.pauta.dto.NovaPautaRequestDTO;
import br.com.guilherme.assembleia.pauta.exceptions.PautaNaoEncontradaException;
import br.com.guilherme.assembleia.pauta.model.Pauta;
import br.com.guilherme.assembleia.pauta.repository.PautaRepository;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço com as funções de pauta
 *
 * @author Guilherme Lacerda
 */
@Service
public class PautaService {

    private PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    /**
     * Cria uma nova pauta no sistema
     *
     * @param novaPautaDTO Objeto com as informações que serão utilizadas na pauta
     * @return Pauta salva
     */
    public Pauta criarPauta(NovaPautaRequestDTO novaPautaDTO) {
        Pauta pauta = new Pauta();
        pauta.setAssunto(novaPautaDTO.getAssunto());

        return pautaRepository.save(pauta);
    }

    /**
     * Busca uma pauta a partir de um id
     *
     * @param id Utilizado para encontrar a pauta
     * @return Pauta encontrada
     */
    public Pauta buscarPautaPorId(Integer id) {
        return pautaRepository.findById(id).orElseThrow(PautaNaoEncontradaException::new);
    }
}
