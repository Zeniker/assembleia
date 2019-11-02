package br.com.guilherme.assembleia.controller;

import br.com.guilherme.assembleia.commons.dto.ResponseDTO;
import br.com.guilherme.assembleia.commons.enums.StatusResposta;
import br.com.guilherme.assembleia.controller.PautaController;
import br.com.guilherme.assembleia.pauta.dto.NovaPautaRequestDTO;
import br.com.guilherme.assembleia.pauta.dto.NovaPautaResponseDTO;
import br.com.guilherme.assembleia.pauta.model.Pauta;
import br.com.guilherme.assembleia.pauta.service.PautaServiceImpl;
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
        ResponseDTO<NovaPautaResponseDTO> responseDTO = pautaController.criarPauta(new NovaPautaRequestDTO());

        //then
        then(pautaService).should().criarPauta(any(NovaPautaRequestDTO.class));
        assertThat(responseDTO.getStatus()).isEqualTo(StatusResposta.SUCESSO);
        assertThat(responseDTO.getMensagem()).isEqualTo("Pauta criada com sucesso");
        assertThat(responseDTO.getData().getId()).isEqualTo(1);
    }

    @DisplayName("Teste criar pauta inválida")
    @Test
    void criarPautaInvalida() {
        //given
        doThrow(RuntimeException.class).when(pautaService).criarPauta(any(NovaPautaRequestDTO.class));

        //when
        ResponseDTO<NovaPautaResponseDTO> responseDTO = pautaController.criarPauta(new NovaPautaRequestDTO());

        //then
        then(pautaService).should().criarPauta(any(NovaPautaRequestDTO.class));
        assertThat(responseDTO.getStatus()).isEqualTo(StatusResposta.ERRO);
        assertThat(responseDTO.getData()).isNull();

    }
}