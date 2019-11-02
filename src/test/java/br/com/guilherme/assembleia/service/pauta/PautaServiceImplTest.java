package br.com.guilherme.assembleia.service.pauta;

import br.com.guilherme.assembleia.dto.pauta.NovaPautaRequestDTO;
import br.com.guilherme.assembleia.pauta.exceptions.PautaNaoEncontradaException;
import br.com.guilherme.assembleia.pauta.model.Pauta;
import br.com.guilherme.assembleia.pauta.repository.PautaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class PautaServiceImplTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private PautaServiceImpl pautaService;

    @Captor
    private ArgumentCaptor<Pauta> captor;

    private Pauta pauta;

    @BeforeEach
    void setUp() {
        pauta = new Pauta();
    }

    @DisplayName("Teste criar nova pauta")
    @Test
    void criarPauta() {
        //given
        given(pautaRepository.save(captor.capture())).willReturn(pauta);

        //when
        NovaPautaRequestDTO novaPautaDTO = new NovaPautaRequestDTO();
        novaPautaDTO.setAssunto("Assunto");

        Pauta pautaSalva = pautaService.criarPauta(novaPautaDTO);

        //then
        then(pautaRepository).should().save(any(Pauta.class));
        assertThat(pautaSalva).isNotNull();
        assertThat(captor.getValue().getAssunto()).isEqualTo("Assunto");
    }

    @DisplayName("Teste buscar pauta por Id")
    @Test
    void buscarPautaPorId() {
        //given
        given(pautaRepository.findById(1)).willReturn(Optional.of(pauta));

        //when
        Pauta pautaEncontrada = pautaService.buscarPautaPorId(1);

        //then
        then(pautaRepository).should().findById(any(Integer.class));
        assertThat(pautaEncontrada).isNotNull();
    }

    @DisplayName("Teste buscar pauta por Id inválido")
    @Test
    void buscarPautaPorIdInvalido() {
        //given
        given(pautaRepository.findById(any())).willReturn(Optional.empty());

        //when
        assertThrows(PautaNaoEncontradaException.class, () -> pautaService.buscarPautaPorId(1));

        //then
        then(pautaRepository).should().findById(any());
    }
}