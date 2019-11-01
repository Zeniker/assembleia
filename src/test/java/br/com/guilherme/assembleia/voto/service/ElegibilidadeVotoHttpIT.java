package br.com.guilherme.assembleia.voto.service;

import br.com.guilherme.assembleia.TesteIntegracao;
import br.com.guilherme.assembleia.voto.dto.CPFStatusDTO;
import br.com.guilherme.assembleia.voto.exception.CPFInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ElegibilidadeVotoHttpIT implements TesteIntegracao {

    private ElegibilidadeVoto elegibilidadeVoto;

    @BeforeEach
    void setUp() {
        elegibilidadeVoto = new ElegibilidadeVotoHttp();
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