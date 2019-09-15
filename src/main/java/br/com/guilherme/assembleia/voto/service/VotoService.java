package br.com.guilherme.assembleia.voto.service;

import br.com.guilherme.assembleia.sessao.exceptions.SessaoFechadaException;
import br.com.guilherme.assembleia.sessao.model.Sessao;
import br.com.guilherme.assembleia.sessao.service.SessaoService;
import br.com.guilherme.assembleia.voto.dto.CPFStatusDTO;
import br.com.guilherme.assembleia.voto.dto.RegistrarVotoRequestDTO;
import br.com.guilherme.assembleia.voto.exception.AssociadoJaVotouException;
import br.com.guilherme.assembleia.voto.exception.AssociadoNaoElegivelException;
import br.com.guilherme.assembleia.voto.model.Voto;
import br.com.guilherme.assembleia.voto.repository.VotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe de serviço com as funções de voto
 *
 * @author Guilherme Lacerda
 */
@Service
public class VotoService {

    private VotoRepository votoRepository;

    private SessaoService sessaoService;

    private ElegibilidadeVoto elegibilidadeVoto;

    public VotoService(VotoRepository votoRepository, SessaoService sessaoService, ElegibilidadeVoto elegibilidadeVoto) {
        this.votoRepository = votoRepository;
        this.sessaoService = sessaoService;
        this.elegibilidadeVoto = elegibilidadeVoto;
    }

    /**
     * Registra um novo voto para uma sessão em aberto
     *
     * @param registrarVotoDTO Objeto com as informações que serão utilizadas no voto
     * @return Voto registrado
     */
    public Voto registrarVoto(RegistrarVotoRequestDTO registrarVotoDTO) {
        Sessao sessao = sessaoService.buscarSessaoPorId(registrarVotoDTO.getSessaoVotada());

        validaVotacao(sessao, registrarVotoDTO.getCpf());

        Voto voto = new Voto();
        voto.setCpfAssociado(registrarVotoDTO.getCpf());
        voto.setEscolha(registrarVotoDTO.getEscolha());
        voto.setSessao(sessao);

        return votoRepository.save(voto);
    }

    private void validaVotacao(Sessao sessao, String cpfAssociado){
        if(!sessao.isSessaoAberta()) throw new SessaoFechadaException();

        if(votoRepository.findByCpfAssociadoAndSessao(cpfAssociado, sessao).isPresent())
            throw new AssociadoJaVotouException();

        CPFStatusDTO statusDTO = elegibilidadeVoto.associadoPodeVotar(cpfAssociado);

        if(!"ABLE_TO_VOTE".equals(statusDTO.getStatus()))
            throw new AssociadoNaoElegivelException();
    }


    /**
     * Busca os votos realizados em uma sessão
     *
     * @param idSessao Utilizado para encontrar a sessão dos votos
     * @return Lista de votos encontrados
     */
    public List<Voto> buscarVotosDaSessao(Integer idSessao) {
        Sessao sessao = sessaoService.buscarSessaoPorId(idSessao);

        return votoRepository.findBySessao(sessao);
    }
}
