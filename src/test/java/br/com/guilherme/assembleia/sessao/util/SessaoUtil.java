package br.com.guilherme.assembleia.sessao.util;

import br.com.guilherme.assembleia.sessao.dto.AbrirSessaoRequestDTO;
import br.com.guilherme.assembleia.sessao.model.Sessao;

public class SessaoUtil {

    public static AbrirSessaoRequestDTO criaSessaoDTO(Integer duracao){
        AbrirSessaoRequestDTO sessaoDTO = new AbrirSessaoRequestDTO();
        sessaoDTO.setPauta(1);
        sessaoDTO.setDuracaoSessao(duracao);

        return sessaoDTO;
    }
}
