package br.com.guilherme.assembleia.controller;

import br.com.guilherme.assembleia.dto.sessao.AbrirSessaoRequestDTO;
import br.com.guilherme.assembleia.dto.sessao.AbrirSessaoResponseDTO;
import br.com.guilherme.assembleia.dto.sessao.ResultadoSessaoDTO;
import br.com.guilherme.assembleia.entity.Sessao;
import br.com.guilherme.assembleia.service.sessao.SessaoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AbrirSessaoResponseDTO> abrirSessao(@RequestBody @Valid AbrirSessaoRequestDTO requestDTO){

        try{
            Sessao sessao = sessaoService.abrirSessao(requestDTO);
            AbrirSessaoResponseDTO responseDTO = new AbrirSessaoResponseDTO(sessao.getId());
            return ResponseEntity.ok(responseDTO);
        }catch (Exception e){
            log.error("Erro ao abrir sessão", e);
            return ResponseEntity.badRequest().body(new AbrirSessaoResponseDTO(e.getMessage()));
        }
    }

    /**
     * Busca o resultado de uma sessão
     *
     * @param id Utilizado para encontrar a sessão
     * @return DTO com mensagem de sucessso e o resultado da sessão, ou em caso de erro, uma mensagem de erro
     */
    @GetMapping("{id}/resultado")
    public ResponseEntity<ResultadoSessaoDTO> buscarResultadoSessao(@PathVariable Integer id){

        try{
            ResultadoSessaoDTO resultado = sessaoService.buscarResultadoSessao(id);
            return ResponseEntity.ok(resultado);
        }catch (Exception e){
            log.error("Erro ao buscar o resultado da sessão", e);
            return ResponseEntity.badRequest().body(new ResultadoSessaoDTO(e.getMessage()));
        }
    }

}
