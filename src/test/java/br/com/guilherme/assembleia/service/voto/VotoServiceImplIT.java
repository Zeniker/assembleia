package br.com.guilherme.assembleia.service.voto;

import br.com.guilherme.assembleia.TesteIntegracao;
import br.com.guilherme.assembleia.dto.sessao.AbrirSessaoRequestDTO;
import br.com.guilherme.assembleia.sessao.model.Sessao;
import br.com.guilherme.assembleia.service.sessao.SessaoServiceImpl;
import br.com.guilherme.assembleia.util.SessaoUtil;
import br.com.guilherme.assembleia.dto.voto.RegistrarVotoRequestDTO;
import br.com.guilherme.assembleia.voto.model.Voto;
import br.com.guilherme.assembleia.enums.VotoEscolha;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class VotoServiceImplIT implements TesteIntegracao {

    @Autowired
    private VotoServiceImpl votoService;

    @Autowired
    private SessaoServiceImpl sessaoService;

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