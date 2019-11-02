package br.com.guilherme.assembleia.util;

import br.com.guilherme.assembleia.dto.sessao.AbrirSessaoRequestDTO;

public class SessaoUtil {

    public static AbrirSessaoRequestDTO criaSessaoDTO(Integer duracao){
        AbrirSessaoRequestDTO sessaoDTO = new AbrirSessaoRequestDTO();
        sessaoDTO.setPauta(1);
        sessaoDTO.setDuracaoSessao(duracao);

        return sessaoDTO;
    }
}
