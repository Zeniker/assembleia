package br.com.guilherme.assembleia.controller;

import br.com.guilherme.assembleia.dto.commons.ResponseDTO;
import br.com.guilherme.assembleia.dto.sessao.AbrirSessaoRequestDTO;
import br.com.guilherme.assembleia.dto.sessao.AbrirSessaoResponseDTO;
import br.com.guilherme.assembleia.dto.sessao.ResultadoSessaoDTO;
import br.com.guilherme.assembleia.sessao.model.Sessao;
import br.com.guilherme.assembleia.service.sessao.SessaoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller para expôr as funcionalidades da sessão
 *
 * @author Guilherme Lacerda
 */
@Slf4j
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
            log.error("Erro ao abrir sessão", e);
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
            log.error("Erro ao buscar o resultado da sessão", e);
            response = new ResponseDTO<>(e);
        }

        return response;
    }

}
