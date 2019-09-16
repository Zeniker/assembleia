package br.com.guilherme.assembleia.voto.service;

import br.com.guilherme.assembleia.voto.dto.CPFStatusDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Primary
@Component
public class ElegibilidadeVotoTest implements ElegibilidadeVoto {

    /**
     * Criada com propósito de teste, sempre irá retornar ABLE_TO_VOTE
     *
     * @param cpf Este CPF será ignorado, mesmo que não seja válido
     * @return Status do cpf
     */
    @Override
    public CPFStatusDTO associadoPodeVotar(String cpf) {
        CPFStatusDTO statusDTO = new CPFStatusDTO();
        statusDTO.setStatus("ABLE_TO_VOTE");

        return statusDTO;
    }
}
