package br.com.guilherme.assembleia.sessao.service;

import br.com.guilherme.assembleia.pauta.model.Pauta;
import br.com.guilherme.assembleia.pauta.service.PautaService;
import br.com.guilherme.assembleia.sessao.dto.AbrirSessaoDTO;
import br.com.guilherme.assembleia.sessao.exceptions.SessaoNaoEncontradaException;
import br.com.guilherme.assembleia.sessao.model.Sessao;
import br.com.guilherme.assembleia.sessao.repository.SessaoRepository;
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
class SessaoServiceTest {

    @Mock
    private SessaoRepository sessaoRepository;

    @Mock
    private PautaService pautaService;

    @InjectMocks
    private SessaoService sessaoService;

    @Captor
    private ArgumentCaptor<Sessao> captor;

    private Sessao sessao;

    @BeforeEach
    void setUp() {
        sessao = new Sessao();
    }

    @DisplayName("Teste abrir sessão")
    @Test
    void abrirSessao() {
        //given
        Pauta pauta = new Pauta();
        pauta.setId(1);

        AbrirSessaoDTO abrirSessaoDTO = new AbrirSessaoDTO();
        abrirSessaoDTO.setPauta(1);
        given(sessaoRepository.save(captor.capture())).willReturn(sessao);
        given(pautaService.buscarPautaPorId(any(Integer.class))).willReturn(pauta);

        //when
        Sessao sessaoAberta = sessaoService.abrirSessao(abrirSessaoDTO);

        //then
        then(sessaoRepository).should().save(any(Sessao.class));
        assertThat(sessaoAberta).isNotNull();
        assertThat(captor.getValue()).isNotNull();
        assertThat(captor.getValue().isSessaoAberta()).isEqualTo(true);
        assertThat(captor.getValue().getPauta().getId()).isEqualTo(1);
    }

    @DisplayName("Teste buscar sessão por Id")
    @Test
    void buscarSessaoPorId() {
        //given
        given(sessaoRepository.findById(any())).willReturn(Optional.of(sessao));

        //when
        Sessao sessaoEncontrada = sessaoService.buscarSessaoPorId(1);

        //then
        then(sessaoRepository).should().findById(1);
        assertThat(sessaoEncontrada).isNotNull();
    }

    @DisplayName("Teste buscar sessão por Id inválido")
    @Test
    void buscarSessaoPorIdInvalido() {
        //given
        given(sessaoRepository.findById(any())).willReturn(Optional.empty());

        //when
        assertThrows(SessaoNaoEncontradaException.class, () -> sessaoService.buscarSessaoPorId(1));

        //then
        then(sessaoRepository).should().findById(1);
    }

    @Test
    void fecharSessao() {
        //given
        sessao.setId(1);
        sessao.setSessaoAberta(true);
        given(sessaoRepository.findById(any(Integer.class))).willReturn(Optional.of(sessao));
        given(sessaoRepository.save(any())).willReturn(sessao);

        //when
        sessaoService.fecharSessao(sessao.getId());

        //then
        then(sessaoRepository).should().findById(any(Integer.class));
        then(sessaoRepository).should().save(any());
        assertThat(sessao.isSessaoAberta()).isEqualTo(false);

    }
}