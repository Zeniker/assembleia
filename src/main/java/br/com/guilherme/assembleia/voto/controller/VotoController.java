package br.com.guilherme.assembleia.voto.controller;

import br.com.guilherme.assembleia.commons.dto.ResponseDTO;
import br.com.guilherme.assembleia.voto.dto.RegistrarVotoRequestDTO;
import br.com.guilherme.assembleia.voto.dto.RegistrarVotoResponseDTO;
import br.com.guilherme.assembleia.voto.model.Voto;
import br.com.guilherme.assembleia.voto.service.VotoServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller para expor as funcionalidades do voto
 *
 * @author Guilherme Lacerda
 */
@RestController
@RequestMapping("voto")
public class VotoController {

    private VotoServiceImpl votoService;

    public VotoController(VotoServiceImpl votoService) {
        this.votoService = votoService;
    }

    /**
     * Registra o voto em uma sessão
     *
     * @param requestDTO Objeto com as informações que serão utilizadas no voto
     * @return DTO com mensagem de sucessso e os dados do voto registrado, ou em caso de erro, uma mensagem de erro
     */
    @PostMapping
    public ResponseDTO<RegistrarVotoResponseDTO> registrarVoto(@RequestBody @Valid RegistrarVotoRequestDTO requestDTO){
        ResponseDTO<RegistrarVotoResponseDTO> response;

        try{
            Voto voto = votoService.registrarVoto(requestDTO);
            RegistrarVotoResponseDTO responseDTO = new RegistrarVotoResponseDTO(voto.getId());
            response = new ResponseDTO<>("Voto registrado com sucesso", responseDTO);
        }catch (Exception e){
            response = new ResponseDTO<>(e);
        }

        return response;
    }
}
