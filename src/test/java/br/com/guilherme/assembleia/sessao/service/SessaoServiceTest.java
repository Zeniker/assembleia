package br.com.guilherme.assembleia.sessao.service;

import br.com.guilherme.assembleia.pauta.model.Pauta;
import br.com.guilherme.assembleia.pauta.service.PautaService;
import br.com.guilherme.assembleia.sessao.dto.AbrirSessaoDTO;
import br.com.guilherme.assembleia.sessao.model.Sessao;
import br.com.guilherme.assembleia.sessao.repository.SessaoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
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

    @DisplayName("Teste abrir sessão")
    @Test
    void abrirSessao() {
        //given
        Sessao sessao = new Sessao();
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
}