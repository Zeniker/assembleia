package br.com.guilherme.assembleia.service.pauta;

import br.com.guilherme.assembleia.TesteIntegracao;
import br.com.guilherme.assembleia.dto.pauta.NovaPautaRequestDTO;
import br.com.guilherme.assembleia.entity.Pauta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class PautaServiceImplIT implements TesteIntegracao {

    @Autowired
    private PautaServiceImpl pautaService;

    private NovaPautaRequestDTO pautaDTO;

    @BeforeEach
    void setUp() {
        pautaDTO = new NovaPautaRequestDTO();
        pautaDTO.setAssunto("Novo assunto");
    }

    @Test
    void criarPauta() {
        Pauta pauta = pautaService.criarPauta(pautaDTO);

        assertThat(pauta).isNotNull();
        assertThat(pauta.getId()).isNotNull();
        assertThat(pauta.getAssunto()).isEqualTo("Novo assunto");
    }

    @Test
    void buscarPautaPorId() {
        Pauta pautaEncontrada = pautaService.buscarPautaPorId(1);

        assertThat(pautaEncontrada.getAssunto()).isEqualTo("Assunto");
    }
}