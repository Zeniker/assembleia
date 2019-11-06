package br.com.guilherme.assembleia.controller;

import br.com.guilherme.assembleia.dto.commons.ResponseDTO;
import br.com.guilherme.assembleia.dto.voto.RegistrarVotoRequestDTO;
import br.com.guilherme.assembleia.dto.voto.RegistrarVotoResponseDTO;
import br.com.guilherme.assembleia.entity.Voto;
import br.com.guilherme.assembleia.service.voto.VotoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
@Slf4j
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
    public ResponseEntity<RegistrarVotoResponseDTO> registrarVoto(@RequestBody @Valid RegistrarVotoRequestDTO requestDTO){

        try{
            Voto voto = votoService.registrarVoto(requestDTO);
            RegistrarVotoResponseDTO responseDTO = new RegistrarVotoResponseDTO(voto.getId());
            return ResponseEntity.ok(responseDTO);
        }catch (Exception e){
            log.error("Erro ao registrar voto", e);
            return ResponseEntity.badRequest().body(new RegistrarVotoResponseDTO(e.getMessage()));
        }
    }
}
