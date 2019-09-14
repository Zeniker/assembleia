package br.com.guilherme.assembleia.pauta.service;

import br.com.guilherme.assembleia.pauta.dto.NovaPautaRequestDTO;
import br.com.guilherme.assembleia.pauta.exceptions.PautaNaoEncontradaException;
import br.com.guilherme.assembleia.pauta.model.Pauta;
import br.com.guilherme.assembleia.pauta.repository.PautaRepository;
import org.springframework.stereotype.Service;

@Service
public class PautaService {

    private PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public Pauta criarPauta(NovaPautaRequestDTO novaPautaDTO) {
        Pauta pauta = new Pauta();
        pauta.setAssunto(novaPautaDTO.getAssunto());

        return pautaRepository.save(pauta);
    }

    public Pauta buscarPautaPorId(Integer id) {
        return pautaRepository.findById(id).orElseThrow(PautaNaoEncontradaException::new);
    }
}
