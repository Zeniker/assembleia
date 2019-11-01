package br.com.guilherme.assembleia.sessao.service;

import br.com.guilherme.assembleia.sessao.dto.AbrirSessaoRequestDTO;
import br.com.guilherme.assembleia.sessao.dto.ResultadoSessaoDTO;
import br.com.guilherme.assembleia.sessao.model.Sessao;

public interface SessaoService {

    /**
     * Abre uma nova sessão de votação no sistema
     *
     * @param abrirSessaoDTO Objeto com as informações que serão utilizadas na sessão
     * @return Sessão aberta
     */
    Sessao abrirSessao(AbrirSessaoRequestDTO abrirSessaoDTO);

    /**
     * Busca uma sessão a partir de um id
     *
     * @param id Utilizado para encontrar a sessão
     * @return Sessão encontrada
     */
    Sessao buscarSessaoPorId(Integer id);

    /**
     * Valida se uma sessão ainda está aberta para votação
     *
     * @param sessao Sessao para validar
     * @return boolean informando se está aberta
     */
    boolean isSessaoAberta(Sessao sessao);

    /**
     * Busca o resultado de uma sessão fechada
     *
     * @param id Utilizado para encontrar a sessão e seus dados
     * @return Objeto com a contabilização de votos da sessão e sua situação
     */
    ResultadoSessaoDTO buscarResultadoSessao(Integer id);
}
