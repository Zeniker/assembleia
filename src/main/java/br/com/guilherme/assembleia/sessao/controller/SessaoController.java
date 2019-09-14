package br.com.guilherme.assembleia.sessao.controller;

import br.com.guilherme.assembleia.commons.dto.ResponseDTO;
import br.com.guilherme.assembleia.sessao.dto.AbrirSessaoRequestDTO;
import br.com.guilherme.assembleia.sessao.dto.AbrirSessaoResponseDTO;
import br.com.guilherme.assembleia.sessao.model.Sessao;
import br.com.guilherme.assembleia.sessao.service.SessaoService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("sessao")
public class SessaoController {

    private SessaoService sessaoService;

    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @PostMapping("abrir")
    public ResponseDTO<AbrirSessaoResponseDTO> abrirSessao(@RequestBody @Valid AbrirSessaoRequestDTO requestDTO){
        ResponseDTO<AbrirSessaoResponseDTO> response;

        try{
            Sessao sessao = sessaoService.abrirSessao(requestDTO);
            AbrirSessaoResponseDTO responseDTO = new AbrirSessaoResponseDTO(sessao.getId());
            response = new ResponseDTO<>("Sessão aberta com sucesso", responseDTO);
        }catch (Exception e){
            response = new ResponseDTO<>(e);
        }

        return response;
    }

}
