package br.com.guilherme.assembleia.controller;

import br.com.guilherme.assembleia.dto.commons.ResponseDTO;
import br.com.guilherme.assembleia.enums.StatusResposta;
import br.com.guilherme.assembleia.dto.pauta.NovaPautaRequestDTO;
import br.com.guilherme.assembleia.dto.pauta.NovaPautaResponseDTO;
import br.com.guilherme.assembleia.entity.Pauta;
import br.com.guilherme.assembleia.service.pauta.PautaServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class PautaControllerTest {

    @Mock
    private PautaServiceImpl pautaService;

    @InjectMocks
    private PautaController pautaController;

    @DisplayName("Teste criar pauta")
    @Test
    void criarPauta() {
        //given
        Pauta pauta = new Pauta();
        pauta.setId(1);

        given(pautaService.criarPauta(any(NovaPautaRequestDTO.class))).willReturn(pauta);

        //when
        ResponseEntity<NovaPautaResponseDTO> responseDTO = pautaController.criarPauta(new NovaPautaRequestDTO());

        //then
        then(pautaService).should().criarPauta(any(NovaPautaRequestDTO.class));
        assertThat(responseDTO.getBody()).isNotNull();
        assertThat(responseDTO.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseDTO.getBody().getId()).isEqualTo(1);
    }

    @DisplayName("Teste criar pauta inválida")
    @Test
    void criarPautaInvalida() {
        //given
        doThrow(RuntimeException.class).when(pautaService).criarPauta(any(NovaPautaRequestDTO.class));

        //when
        ResponseEntity<NovaPautaResponseDTO> responseDTO = pautaController.criarPauta(new NovaPautaRequestDTO());

        //then
        then(pautaService).should().criarPauta(any(NovaPautaRequestDTO.class));
        assertThat(responseDTO.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseDTO.getBody()).isNotNull();

    }
}