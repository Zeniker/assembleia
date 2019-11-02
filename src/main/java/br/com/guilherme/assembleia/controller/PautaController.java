package br.com.guilherme.assembleia.controller;

import br.com.guilherme.assembleia.dto.commons.ResponseDTO;
import br.com.guilherme.assembleia.dto.pauta.NovaPautaRequestDTO;
import br.com.guilherme.assembleia.dto.pauta.NovaPautaResponseDTO;
import br.com.guilherme.assembleia.pauta.model.Pauta;
import br.com.guilherme.assembleia.service.pauta.PautaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller para expor as funcionalidades da pauta
 *
 * @author Guilherme Lacerda
 */
@Slf4j
@RestController
@RequestMapping("pauta")
public class PautaController {

    private PautaServiceImpl pautaService;

    public PautaController(PautaServiceImpl pautaService) {
        this.pautaService = pautaService;
    }

    /**
     * Permite a criação de uma nova pauta
     *
     * @param requestDTO Objeto com as informações que serão utilizadas na pauta
     * @return DTO com mensagem de sucessso e os dados da nova pauta, ou em caso de erro, uma mensagem de erro
     */
    @PostMapping
    public ResponseDTO<NovaPautaResponseDTO> criarPauta(@RequestBody @Valid NovaPautaRequestDTO requestDTO){
        ResponseDTO<NovaPautaResponseDTO> response;

        try{
            Pauta pauta = pautaService.criarPauta(requestDTO);
            NovaPautaResponseDTO responseDTO = new NovaPautaResponseDTO();
            responseDTO.setId(pauta.getId());

            response = new ResponseDTO<>("Pauta criada com sucesso", responseDTO);

        }catch (Exception e){
            log.error("Erro ao criar pauta", e);
            response = new ResponseDTO<>(e);
        }

        return response;
    }
}
