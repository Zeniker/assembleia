package br.com.guilherme.assembleia.service.voto;

import br.com.guilherme.assembleia.voto.dto.CPFStatusDTO;
import br.com.guilherme.assembleia.voto.exception.CPFInvalidoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class ElegibilidadeVotoHttp implements ElegibilidadeVoto {

    private RestTemplate restTemplate;

    private final String URI = "https://user-info.herokuapp.com/users/{cpf}";

    public ElegibilidadeVotoHttp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Verifica em um serviço de terceiro se o cpf informado está elegível para voto e retorna seu status
     *
     * @param cpf CPF a ser verificado
     * @return Status do cpf
     */
    public CPFStatusDTO associadoPodeVotar(String cpf){

        try{
            ResponseEntity<CPFStatusDTO> entity = restTemplate.getForEntity(URI, CPFStatusDTO.class, cpf);

            if(!entity.getStatusCode().is2xxSuccessful()) throw new CPFInvalidoException();

            return entity.getBody();
        }catch (RuntimeException e){
            String mensagem = "Não foi possível consultar o CPF do associado: " + cpf;
            log.error(mensagem, e);

            throw new CPFInvalidoException();
        }
    }
}
