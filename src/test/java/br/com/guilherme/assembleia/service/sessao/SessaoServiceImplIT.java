package br.com.guilherme.assembleia.service.sessao;

import br.com.guilherme.assembleia.TesteIntegracao;
import br.com.guilherme.assembleia.service.sessao.SessaoServiceImpl;
import br.com.guilherme.assembleia.sessao.dto.AbrirSessaoRequestDTO;
import br.com.guilherme.assembleia.sessao.dto.ResultadoSessaoDTO;
import br.com.guilherme.assembleia.sessao.model.Sessao;
import br.com.guilherme.assembleia.sessao.util.SessaoUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class SessaoServiceImplIT implements TesteIntegracao {

    @Autowired
    private SessaoServiceImpl sessaoService;

    @DisplayName("Teste abriu sessão no BD")
    @Test
    void abrirSessao() {
        AbrirSessaoRequestDTO sessaoDTO = SessaoUtil.criaSessaoDTO(5);

        Sessao sessao = sessaoService.abrirSessao(sessaoDTO);

        assertThat(sessao).isNotNull();
        assertThat(sessao.getPauta().getId()).isEqualTo(1);
        assertThat(sessao.getDataHoraAbertura()).isNotNull();
        assertThat(sessao.getDataHoraFechamento()).isNotNull();
    }

    @DisplayName("Teste buscou sessão no BD")
    @Test
    void buscarSessaoPorId() {
        Sessao sessaoEncontrada = sessaoService.buscarSessaoPorId(1);

        assertThat(sessaoEncontrada).isNotNull();
        assertThat(sessaoEncontrada.getId()).isNotNull();
        assertThat(sessaoEncontrada.getPauta().getId()).isEqualTo(1);
    }

    @DisplayName("Teste sessão aberta no BD")
    @Test
    void isSessaoAberta() {
        Sessao sessao = sessaoService.buscarSessaoPorId(1);

        assertThat(sessaoService.isSessaoAberta(sessao)).isEqualTo(false);
    }

    @DisplayName("Teste buscar resultado sessão do BD")
    @Test
    void buscarResultadoSessao() {
        ResultadoSessaoDTO resultadoSessaoDTO = sessaoService.buscarResultadoSessao(2);

        assertThat(resultadoSessaoDTO).isNotNull();
        assertThat(resultadoSessaoDTO.getTotalVotos()).isEqualTo(2);
        assertThat(resultadoSessaoDTO.getTotalVotosAFavor()).isEqualTo(1);
        assertThat(resultadoSessaoDTO.getTotalVotosContra()).isEqualTo(1);
    }
}