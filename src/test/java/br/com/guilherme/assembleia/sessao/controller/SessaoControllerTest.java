package br.com.guilherme.assembleia.sessao.controller;

import br.com.guilherme.assembleia.commons.dto.ResponseDTO;
import br.com.guilherme.assembleia.commons.dto.StatusResposta;
import br.com.guilherme.assembleia.pauta.exceptions.PautaNaoEncontradaException;
import br.com.guilherme.assembleia.sessao.dto.AbrirSessaoRequestDTO;
import br.com.guilherme.assembleia.sessao.dto.AbrirSessaoResponseDTO;
import br.com.guilherme.assembleia.sessao.model.Sessao;
import br.com.guilherme.assembleia.sessao.service.SessaoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class SessaoControllerTest {

    @Mock
    private SessaoService sessaoService;

    @InjectMocks
    private SessaoController sessaoController;

    @DisplayName("Teste abrir sessão")
    @Test
    void abrirSessao() {
        //given
        Sessao sessao = new Sessao();
        sessao.setId(1);

        given(sessaoService.abrirSessao(any(AbrirSessaoRequestDTO.class))).willReturn(sessao);

        //when
        ResponseDTO<AbrirSessaoResponseDTO> responseDTO = sessaoController.abrirSessao(new AbrirSessaoRequestDTO());

        //then
        then(sessaoService).should().abrirSessao(any(AbrirSessaoRequestDTO.class));
        assertThat(responseDTO.getStatus()).isEqualTo(StatusResposta.SUCESSO);
        assertThat(responseDTO.getMensagem()).isEqualTo("Sessão aberta com sucesso");
        assertThat(responseDTO.getData().getId()).isEqualTo(1);
    }

    @DisplayName("Teste abrir sessão inválida")
    @Test
    void abrirSessaoInvalida() {
        //given
        doThrow(RuntimeException.class).when(sessaoService).abrirSessao(any(AbrirSessaoRequestDTO.class));

        //when
        ResponseDTO<AbrirSessaoResponseDTO> responseDTO = sessaoController.abrirSessao(new AbrirSessaoRequestDTO());

        //then
        then(sessaoService).should().abrirSessao(any(AbrirSessaoRequestDTO.class));
        assertThat(responseDTO.getStatus()).isEqualTo(StatusResposta.ERRO);
    }
}