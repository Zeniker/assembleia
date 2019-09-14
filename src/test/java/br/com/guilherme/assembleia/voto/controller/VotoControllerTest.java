package br.com.guilherme.assembleia.voto.controller;

import br.com.guilherme.assembleia.commons.dto.ResponseDTO;
import br.com.guilherme.assembleia.commons.dto.StatusResposta;
import br.com.guilherme.assembleia.voto.dto.RegistrarVotoRequestDTO;
import br.com.guilherme.assembleia.voto.dto.RegistrarVotoResponseDTO;
import br.com.guilherme.assembleia.voto.model.Voto;
import br.com.guilherme.assembleia.voto.service.VotoService;
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
class VotoControllerTest {

    @Mock
    private VotoService votoService;

    @InjectMocks
    private VotoController votoController;

    @DisplayName("Teste registro de voto")
    @Test
    void registrarVoto() {
        Voto voto = new Voto();
        voto.setId(1);

        //given
        given(votoService.registrarVoto(any(RegistrarVotoRequestDTO.class))).willReturn(voto);

        //when
        ResponseDTO<RegistrarVotoResponseDTO> responseDTO = votoController.registrarVoto(new RegistrarVotoRequestDTO());

        //then
        then(votoService).should().registrarVoto(any(RegistrarVotoRequestDTO.class));
        assertThat(responseDTO.getStatus()).isEqualTo(StatusResposta.SUCESSO);
        assertThat(responseDTO.getMensagem()).isEqualTo("Voto registrado com sucesso");
        assertThat(responseDTO.getData().getId()).isEqualTo(1);
    }

    @DisplayName("Teste registro de voto inválido")
    @Test
    void registrarVotoInvalido() {
        //given
        doThrow(RuntimeException.class).when(votoService).registrarVoto(any(RegistrarVotoRequestDTO.class));

        //when
        ResponseDTO<RegistrarVotoResponseDTO> responseDTO = votoController.registrarVoto(new RegistrarVotoRequestDTO());

        //then
        then(votoService).should().registrarVoto(any(RegistrarVotoRequestDTO.class));
        assertThat(responseDTO.getStatus()).isEqualTo(StatusResposta.ERRO);
    }
}