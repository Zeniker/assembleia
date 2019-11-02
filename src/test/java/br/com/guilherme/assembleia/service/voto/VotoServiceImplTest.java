package br.com.guilherme.assembleia.service.voto;

import br.com.guilherme.assembleia.exception.SessaoFechadaException;
import br.com.guilherme.assembleia.entity.Sessao;
import br.com.guilherme.assembleia.service.sessao.SessaoServiceImpl;
import br.com.guilherme.assembleia.dto.voto.CPFStatusDTO;
import br.com.guilherme.assembleia.dto.voto.RegistrarVotoRequestDTO;
import br.com.guilherme.assembleia.exception.AssociadoJaVotouException;
import br.com.guilherme.assembleia.exception.AssociadoNaoElegivelException;
import br.com.guilherme.assembleia.entity.Voto;
import br.com.guilherme.assembleia.enums.VotoEscolha;
import br.com.guilherme.assembleia.repository.VotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
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
class VotoServiceImplTest {

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private SessaoServiceImpl sessaoService;

    @Mock
    private ElegibilidadeVoto elegibilidadeVoto;

    @InjectMocks
    private VotoServiceImpl votoService;

    @Captor
    private ArgumentCaptor<Voto> captor;

    private Sessao sessao;
    private RegistrarVotoRequestDTO registrarVotoDTO;

    @BeforeEach
    void setUp() {
        sessao = new Sessao();
        sessao.setId(1);

        registrarVotoDTO = new RegistrarVotoRequestDTO();
        registrarVotoDTO.setCpf("72332314431");
        registrarVotoDTO.setEscolha(VotoEscolha.SIM);
        registrarVotoDTO.setSessaoVotada(1);
    }

    @DisplayName("Teste registro voto")
    @Test
    void registrarVoto() {
        //given
        Voto voto = new Voto();
        sessao.setDataHoraFechamento(LocalDateTime.now().plusDays(1));

        CPFStatusDTO cpfStatusDTO = new CPFStatusDTO();
        cpfStatusDTO.setStatus("ABLE_TO_VOTE");

        given(votoRepository.save(captor.capture())).willReturn(voto);
        given(sessaoService.buscarSessaoPorId(any())).willReturn(sessao);
        given(sessaoService.isSessaoAberta(any())).willReturn(true);
        given(elegibilidadeVoto.associadoPodeVotar(anyString())).willReturn(cpfStatusDTO);

        //when
        Voto votoRegistrado = votoService.registrarVoto(registrarVotoDTO);

        //then
        then(votoRepository).should().save(any(Voto.class));
        then(votoRepository).should().findByCpfAssociadoAndSessao(anyString(), any(Sessao.class));
        then(sessaoService).should().buscarSessaoPorId(1);
        then(sessaoService).should().isSessaoAberta(any());
        assertThat(votoRegistrado).isNotNull();
        assertThat(captor.getValue().getCpfAssociado()).isEqualTo("72332314431");
        assertThat(captor.getValue().getEscolha()).isEqualTo(VotoEscolha.SIM);
        assertThat(captor.getValue().getSessao().getId()).isEqualTo(1);
    }

    @DisplayName("Teste registro voto em sessão fechada")
    @Test
    void registrarVotoSessaoFechada() {
        //given
        given(sessaoService.isSessaoAberta(any())).willReturn(false);
        given(sessaoService.buscarSessaoPorId(any())).willReturn(sessao);

        //when
        assertThrows(SessaoFechadaException.class, () -> votoService.registrarVoto(registrarVotoDTO));

        //then
        then(sessaoService).should().isSessaoAberta(any());
        then(sessaoService).should().buscarSessaoPorId(1);
    }

    @DisplayName("Teste registro voto já existente na sessão")
    @Test
    void registrarVotoJaExistente() {
        //given
        sessao.setDataHoraFechamento(LocalDateTime.now().plusDays(1));
        given(sessaoService.buscarSessaoPorId(any())).willReturn(sessao);
        given(sessaoService.isSessaoAberta(any())).willReturn(true);
        given(votoRepository.findByCpfAssociadoAndSessao(anyString(), any(Sessao.class))).willReturn(Optional.of(new Voto()));

        //when
        assertThrows(AssociadoJaVotouException.class, () -> votoService.registrarVoto(registrarVotoDTO));

        //then
        then(sessaoService).should().isSessaoAberta(any());
        then(sessaoService).should().buscarSessaoPorId(1);
        then(votoRepository).should().findByCpfAssociadoAndSessao(anyString(), any(Sessao.class));
    }

    @DisplayName("Teste registro voto de cpf não elegível")
    @Test
    void registrarVotoCPFInvalido() {
        //given
        sessao.setDataHoraFechamento(LocalDateTime.now().plusDays(1));
        given(sessaoService.buscarSessaoPorId(any())).willReturn(sessao);
        given(sessaoService.isSessaoAberta(any())).willReturn(true);
        given(elegibilidadeVoto.associadoPodeVotar(anyString())).willReturn(new CPFStatusDTO());

        //when
        assertThrows(AssociadoNaoElegivelException.class, () -> votoService.registrarVoto(registrarVotoDTO));

        //then
        then(sessaoService).should().isSessaoAberta(any());
        then(sessaoService).should().buscarSessaoPorId(1);
        then(elegibilidadeVoto).should().associadoPodeVotar(anyString());
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