package br.com.guilherme.assembleia.voto.service;

import br.com.guilherme.assembleia.voto.dto.CPFStatusDTO;
import br.com.guilherme.assembleia.voto.exception.CPFInvalidoException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ElegibilidadeVotoHttp implements ElegibilidadeVoto {

    /**
     * Verifica em um serviço de terceiro se o cpf informado está elegível para voto e retorna seu status
     *
     * @param cpf CPF a ser verificado
     * @return Status do cpf
     */
    public CPFStatusDTO associadoPodeVotar(String cpf){

        try{
            CloseableHttpClient httpClient = HttpClients.createDefault();

            HttpGet get = new HttpGet("https://user-info.herokuapp.com/users/" + cpf);

            HttpResponse httpResponse = httpClient.execute(get);

            if(httpResponse.getStatusLine().getStatusCode() == 404) throw new CPFInvalidoException();

            String responseBody = EntityUtils.toString(httpResponse.getEntity());

            return new ObjectMapper().readValue(responseBody, CPFStatusDTO.class);

        }catch (IOException e){
            throw new RuntimeException("Não foi possível consultar o CPF do associado");
        }
    }
}
