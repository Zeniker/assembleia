package br.com.guilherme.assembleia.service.voto;

import br.com.guilherme.assembleia.TesteIntegracao;
import br.com.guilherme.assembleia.dto.voto.CPFStatusDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ElegibilidadeVotoTestIT implements TesteIntegracao {

    @Autowired
    private ElegibilidadeVoto elegibilidadeVoto;

    @Test
    void associadoPodeVotar() {
        CPFStatusDTO cpfStatusDTO = elegibilidadeVoto.associadoPodeVotar("");

        assertThat(cpfStatusDTO.getStatus()).isEqualTo("ABLE_TO_VOTE");
    }
}