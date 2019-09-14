package br.com.guilherme.assembleia.pauta.service;

import br.com.guilherme.assembleia.pauta.dto.NovaPautaDTO;
import br.com.guilherme.assembleia.pauta.model.Pauta;
import br.com.guilherme.assembleia.pauta.repository.PautaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class PautaServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private PautaService pautaService;

    @DisplayName("Teste criar nova pauta")
    @Test
    void criarPauta() {
        Pauta pauta = new Pauta();

        //given
        given(pautaRepository.save(any(Pauta.class))).willReturn(pauta);

        //when
        NovaPautaDTO novaPautaDTO = new NovaPautaDTO();
        novaPautaDTO.setAssunto("");

        Pauta pautaSalva = pautaService.criarPauta(novaPautaDTO);

        //then
        then(pautaRepository).should().save(any(Pauta.class));
        assertThat(pautaSalva).isNotNull();

    }
}