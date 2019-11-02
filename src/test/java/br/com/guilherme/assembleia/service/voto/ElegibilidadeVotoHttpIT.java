package br.com.guilherme.assembleia.service.voto;

import br.com.guilherme.assembleia.TesteIntegracao;
import br.com.guilherme.assembleia.dto.voto.CPFStatusDTO;
import br.com.guilherme.assembleia.exception.CPFInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ElegibilidadeVotoHttpIT implements TesteIntegracao {

    @Autowired
    private RestTemplate restTemplate;

    private ElegibilidadeVoto elegibilidadeVoto;

    @BeforeEach
    void setUp() {
        elegibilidadeVoto = new ElegibilidadeVotoHttp(restTemplate);
    }

    @DisplayName("Teste associado pode votar")
    @Test
    void associadoPodeVotar() {

        CPFStatusDTO cpfStatusDTO = elegibilidadeVoto.associadoPodeVotar("77583247977");

        assertThat(cpfStatusDTO).isNotNull();
        assertThat(cpfStatusDTO.getStatus()).isNotNull();
    }

    @DisplayName("Teste associado pode votar com cpf inválido")
    @Test
    void associadoPodeVotarCPFInvalido() {
        assertThrows(CPFInvalidoException.class, () -> elegibilidadeVoto.associadoPodeVotar("cpfinvalido"));
    }
}