package br.com.guilherme.assembleia.voto.service;

import br.com.guilherme.assembleia.TesteIntegracao;
import br.com.guilherme.assembleia.sessao.dto.AbrirSessaoRequestDTO;
import br.com.guilherme.assembleia.sessao.model.Sessao;
import br.com.guilherme.assembleia.sessao.service.SessaoService;
import br.com.guilherme.assembleia.sessao.util.SessaoUtil;
import br.com.guilherme.assembleia.voto.dto.RegistrarVotoRequestDTO;
import br.com.guilherme.assembleia.voto.model.Voto;
import br.com.guilherme.assembleia.voto.model.VotoEscolha;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class VotoServiceIT implements TesteIntegracao {

    @Autowired
    private VotoService votoService;

    @Autowired
    private SessaoService sessaoService;

    @Test
    void registrarVoto() {
        AbrirSessaoRequestDTO sessaoDTO = SessaoUtil.criaSessaoDTO(5);

        Sessao sessao =sessaoService.abrirSessao(sessaoDTO);

        RegistrarVotoRequestDTO votoDTO = new RegistrarVotoRequestDTO();
        votoDTO.setCpf("18986041774");
        votoDTO.setEscolha(VotoEscolha.SIM);
        votoDTO.setSessaoVotada(sessao.getId());

        votoService.registrarVoto(votoDTO);
    }

    @Test
    void buscarVotosDaSessao() {
        List<Voto> votos = votoService.buscarVotosDaSessao(2);

        assertThat(votos).hasSize(2);
    }
}