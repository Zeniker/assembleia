package br.com.guilherme.assembleia.service.voto;

import br.com.guilherme.assembleia.voto.dto.CPFStatusDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Classe para verificar a elegibilidade de voto
 *
 * @author
 */
public interface ElegibilidadeVoto {

    /**
     * Verifica se o cpf informado está elegível para voto e retorna seu status
     *
     * @param cpf CPF a ser verificado
     * @return Status do cpf
     */
    CPFStatusDTO associadoPodeVotar(String cpf);
}
