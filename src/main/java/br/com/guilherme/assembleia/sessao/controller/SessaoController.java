package br.com.guilherme.assembleia.sessao.controller;

import br.com.guilherme.assembleia.commons.dto.ResponseDTO;
import br.com.guilherme.assembleia.sessao.dto.AbrirSessaoRequestDTO;
import br.com.guilherme.assembleia.sessao.dto.AbrirSessaoResponseDTO;
import br.com.guilherme.assembleia.sessao.dto.ResultadoSessaoDTO;
import br.com.guilherme.assembleia.sessao.model.Sessao;
import br.com.guilherme.assembleia.sessao.service.SessaoServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller para expôr as funcionalidades da sessão
 *
 * @author Guilherme Lacerda
 */
@RestController
@RequestMapping("sessao")
public class SessaoController {

    private SessaoServiceImpl sessaoService;

    public SessaoController(SessaoServiceImpl sessaoService) {
        this.sessaoService = sessaoService;
    }

    /**
     * Permite a abertura de uma nova sessão
     *
     * @param requestDTO Objeto com as informações que serão utilizadas na sessão
     * @return DTO com mensagem de sucessso e os dados da nova sessão, ou em caso de erro, uma mensagem de erro
     */
    @PostMapping
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

    /**
     * Busca o resultado de uma sessão
     *
     * @param id Utilizado para encontrar a sessão
     * @return DTO com mensagem de sucessso e o resultado da sessão, ou em caso de erro, uma mensagem de erro
     */
    @GetMapping("/resultado/{id}")
    public ResponseDTO<ResultadoSessaoDTO> buscarResultadoSessao(@PathVariable Integer id){
        ResponseDTO<ResultadoSessaoDTO> response;

        try{
            ResultadoSessaoDTO resultado = sessaoService.buscarResultadoSessao(id);
            response = new ResponseDTO<>("Resultado consultado com sucesso", resultado);
        }catch (Exception e){
            response = new ResponseDTO<>(e);
        }

        return response;
    }

}
