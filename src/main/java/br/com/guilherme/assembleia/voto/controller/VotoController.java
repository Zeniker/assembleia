package br.com.guilherme.assembleia.voto.controller;

import br.com.guilherme.assembleia.commons.dto.ResponseDTO;
import br.com.guilherme.assembleia.voto.dto.RegistrarVotoRequestDTO;
import br.com.guilherme.assembleia.voto.dto.RegistrarVotoResponseDTO;
import br.com.guilherme.assembleia.voto.model.Voto;
import br.com.guilherme.assembleia.voto.service.VotoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("voto")
public class VotoController {

    private VotoService votoService;

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

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
