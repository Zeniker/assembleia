package br.com.guilherme.assembleia.sessao.service;

import br.com.guilherme.assembleia.pauta.model.Pauta;
import br.com.guilherme.assembleia.pauta.service.PautaService;
import br.com.guilherme.assembleia.sessao.dto.AbrirSessaoRequestDTO;
import br.com.guilherme.assembleia.sessao.dto.ResultadoSessaoDTO;
import br.com.guilherme.assembleia.sessao.exceptions.SessaoAbertaException;
import br.com.guilherme.assembleia.sessao.exceptions.SessaoNaoEncontradaException;
import br.com.guilherme.assembleia.sessao.model.Sessao;
import br.com.guilherme.assembleia.sessao.model.SituacaoVotacao;
import br.com.guilherme.assembleia.sessao.repository.SessaoRepository;
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

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SessaoServiceTest {

    @Mock
    private SessaoRepository sessaoRepository;

    @Mock
    private PautaService pautaService;

    @Mock
    private VotoRepository votoRepository;

    @InjectMocks
    private SessaoService sessaoService;

    @Captor
    private ArgumentCaptor<Sessao> captor;

    private Sessao sessao;
    private AbrirSessaoRequestDTO abrirSessaoDTO;
    private Pauta pauta;
    private Voto votoContra;
    private Voto votoAFavor;

    @BeforeEach
    void setUp() {
        sessao = new Sessao();
        sessao.setId(1);
        sessao.setDataHoraAbertura(LocalDateTime.now().minusSeconds(2));
        sessao.setDataHoraFechamento(LocalDateTime.now());

        abrirSessaoDTO = new AbrirSessaoRequestDTO();
        abrirSessaoDTO.setPauta(1);

        pauta = new Pauta();
        pauta.setId(1);


        votoAFavor = new Voto();
        votoAFavor.setEscolha(VotoEscolha.SIM);

        votoContra = new Voto();
        votoContra.setEscolha(VotoEscolha.NAO);
    }

    @DisplayName("Teste abrir sessão")
    @Test
    void abrirSessao() {
        //given
        given(sessaoRepository.save(captor.capture())).willReturn(sessao);
        given(pautaService.buscarPautaPorId(any(Integer.class))).willReturn(pauta);

        //when
        Sessao sessaoAberta = sessaoService.abrirSessao(abrirSessaoDTO);

        //then
        then(sessaoRepository).should().save(any(Sessao.class));
        assertThat(sessaoAberta).isNotNull();
        assertThat(captor.getValue()).isNotNull();
        assertThat(captor.getValue().getDataHoraAbertura()).isNotNull();
        assertThat(captor.getValue().getDataHoraFechamento())
                .isNotNull()
                .isEqualTo(captor.getValue().getDataHoraAbertura().plusSeconds(60));

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

    @DisplayName("Teste busca resultado sessão inválida")
    @Test
    void buscarResultadoSessaoInvalida() {
        //given
        sessao.setDataHoraFechamento(LocalDateTime.now().plusMinutes(1));
        given(sessaoRepository.findById(any())).willReturn(Optional.of(sessao));

        //when
        assertThrows(SessaoAbertaException.class, () -> sessaoService.buscarResultadoSessao(1));

        //then
        then(sessaoRepository).should().findById(any());
    }

    @DisplayName("Teste busca resultado sessão aprovada")
    @Test
    void buscarResultadoSessaoAprovada() {
        //given
        List<Voto> votoList = Arrays.asList(votoAFavor, votoContra, votoAFavor);

        given(sessaoRepository.findById(any())).willReturn(Optional.of(sessao));
        given(votoRepository.findBySessao(any(Sessao.class))).willReturn(votoList);

        //when
        ResultadoSessaoDTO resultadoSessao = sessaoService.buscarResultadoSessao(1);

        //then
        assertThat(resultadoSessao.getTotalVotos()).isEqualTo(3);
        assertThat(resultadoSessao.getTotalVotosAFavor()).isEqualTo(2);
        assertThat(resultadoSessao.getTotalVotosContra()).isEqualTo(1);
        assertThat(resultadoSessao.getSituacao()).isEqualTo(SituacaoVotacao.APROVADA);
    }

    @DisplayName("Teste busca resultado sessão reprovada")
    @Test
    void buscarResultadoSessaoReprovada() {
        //given
        List<Voto> votoList = Arrays.asList(votoAFavor, votoContra, votoContra);

        given(sessaoRepository.findById(any())).willReturn(Optional.of(sessao));
        given(votoRepository.findBySessao(any(Sessao.class))).willReturn(votoList);

        //when
        ResultadoSessaoDTO resultadoSessao = sessaoService.buscarResultadoSessao(1);

        //then
        assertThat(resultadoSessao.getTotalVotos()).isEqualTo(3);
        assertThat(resultadoSessao.getTotalVotosAFavor()).isEqualTo(1);
        assertThat(resultadoSessao.getTotalVotosContra()).isEqualTo(2);
        assertThat(resultadoSessao.getSituacao()).isEqualTo(SituacaoVotacao.REPROVADA);
    }

    @DisplayName("Teste busca resultado sessão empatada")
    @Test
    void buscarResultadoSessaoEmpatada() {
        //given
        List<Voto> votoList = Arrays.asList(votoAFavor, votoContra);

        given(sessaoRepository.findById(any())).willReturn(Optional.of(sessao));
        given(votoRepository.findBySessao(any(Sessao.class))).willReturn(votoList);

        //when
        ResultadoSessaoDTO resultadoSessao = sessaoService.buscarResultadoSessao(1);

        //then
        assertThat(resultadoSessao.getTotalVotos()).isEqualTo(2);
        assertThat(resultadoSessao.getTotalVotosAFavor()).isEqualTo(1);
        assertThat(resultadoSessao.getTotalVotosContra()).isEqualTo(1);
        assertThat(resultadoSessao.getSituacao()).isEqualTo(SituacaoVotacao.EMPATE);
    }

    @DisplayName("Teste verifica estado sessão - Aberta")
    @Test
    void isSessaoAberta() {
        sessao.setDataHoraFechamento(LocalDateTime.now().plusMinutes(1));

        boolean isSessaoAberta = sessaoService.isSessaoAberta(sessao);

        assertThat(isSessaoAberta).isEqualTo(true);
    }

    @DisplayName("Teste verifica estado sessão - Fechada")
    @Test
    void isSessaoAbertaFalse() {
        sessao.setDataHoraFechamento(LocalDateTime.now());

        boolean isSessaoAberta = sessaoService.isSessaoAberta(sessao);

        assertThat(isSessaoAberta).isEqualTo(false);
    }
}