package br.com.guilherme.assembleia.voto.service;

import br.com.guilherme.assembleia.sessao.exceptions.SessaoFechadaException;
import br.com.guilherme.assembleia.sessao.model.Sessao;
import br.com.guilherme.assembleia.sessao.service.SessaoService;
import br.com.guilherme.assembleia.voto.dto.RegistrarVotoDTO;
import br.com.guilherme.assembleia.voto.exception.AssociadoJaVotouException;
import br.com.guilherme.assembleia.voto.model.Voto;
import br.com.guilherme.assembleia.voto.model.VotoEscolha;
import br.com.guilherme.assembleia.voto.repository.VotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class VotoServiceTest {

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private SessaoService sessaoService;

    @InjectMocks
    private VotoService votoService;

    @Captor
    private ArgumentCaptor<Voto> captor;

    private Sessao sessao;
    private RegistrarVotoDTO registrarVotoDTO;

    @BeforeEach
    void setUp() {
        sessao = new Sessao();
        sessao.setId(1);

        registrarVotoDTO = new RegistrarVotoDTO();
        registrarVotoDTO.setCpf("72332314431");
        registrarVotoDTO.setEscolha(VotoEscolha.SIM);
        registrarVotoDTO.setSessaoVotada(1);
    }

    @DisplayName("Teste registro voto")
    @Test
    void registrarVoto() {
        //given
        Voto voto = new Voto();
        sessao.setSessaoAberta(true);

        given(votoRepository.save(captor.capture())).willReturn(voto);
        given(sessaoService.buscarSessaoPorId(any())).willReturn(sessao);

        //when
        Voto votoRegistrado = votoService.registrarVoto(registrarVotoDTO);

        //then
        then(votoRepository).should().save(any(Voto.class));
        then(votoRepository).should().findByCpfAssociado(anyString());
        then(sessaoService).should().buscarSessaoPorId(1);
        assertThat(votoRegistrado).isNotNull();
        assertThat(captor.getValue().getCpfAssociado()).isEqualTo("72332314431");
        assertThat(captor.getValue().getEscolha()).isEqualTo(VotoEscolha.SIM);
        assertThat(captor.getValue().getSessao().getId()).isEqualTo(1);
    }

    @DisplayName("Teste registro voto em sessão fechada")
    @Test
    void registrarVotoSessaoFechada() {
        //given
        given(sessaoService.buscarSessaoPorId(any())).willReturn(sessao);

        //when
        assertThrows(SessaoFechadaException.class, () -> votoService.registrarVoto(registrarVotoDTO));

        //then
        then(sessaoService).should().buscarSessaoPorId(1);
    }

    @DisplayName("Teste registro voto já existente na sessão")
    @Test
    void registrarVotoJaExistente() {
        //given
        sessao.setSessaoAberta(true);
        given(sessaoService.buscarSessaoPorId(any())).willReturn(sessao);
        given(votoRepository.findByCpfAssociado(anyString())).willReturn(Optional.of(new Voto()));

        //when
        assertThrows(AssociadoJaVotouException.class, () -> votoService.registrarVoto(registrarVotoDTO));

        //then
        then(sessaoService).should().buscarSessaoPorId(1);
        then(votoRepository).should().findByCpfAssociado(anyString());
    }

    @DisplayName("Buscar votos registrados para uma sessão")
    @Test
    void buscarVotosDaSessao() {
        //given
        List<Voto> votos = Arrays.asList(new Voto(), new Voto());

        given(sessaoService.buscarSessaoPorId(any())).willReturn(sessao);
        given(votoRepository.findBySessao(any(Sessao.class))).willReturn(votos);

        //when
        List<Voto> votosDaSessao = votoService.buscarVotosDaSessao(1);

        //then
        then(votoRepository).should().findBySessao(any(Sessao.class));
        assertThat(votosDaSessao).hasSize(2);
    }
}