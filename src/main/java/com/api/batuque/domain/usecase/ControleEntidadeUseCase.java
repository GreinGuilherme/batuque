package com.api.batuque.domain.usecase;

import com.api.batuque.domain.model.Entidades;
import com.api.batuque.domain.port.input.ControleEntidadeInputPort;
import com.api.batuque.domain.port.output.EntidadeRepositoryOutputPort;
import com.api.batuque.domain.utils.SyncEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class ControleEntidadeUseCase implements ControleEntidadeInputPort {

    private final EntidadeRepositoryOutputPort entidadeRepositoryOutputPort;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void salvarEntidade(Entidades entidade) {
        log.info("[SALVAR ENTIDADE] - Iniciando processo para salvar entidade: ", entidade.getNomeEntidade());
        entidade.setNomeEntidade(entidade.getNomeEntidade().trim());
        entidadeRepositoryOutputPort.incluirEntidade(entidade);
        messagingTemplate.convertAndSend("/topic/entidade", new SyncEvent<>("CREATE", entidade));
        log.info("[SALVAR ENTIDADE] - Entidade salvo com sucesso.");}

    @Override
    public List<Entidades> buscarEntidades() {
        log.info("[BUSCAR ENTIDADE] - Iniciando processo para buscar todas entidades.");
        List<Entidades> result = entidadeRepositoryOutputPort.buscarEntidades();
        log.info("[BUSCAR ENTIDADE] - Busca por toodas as entidades completa.");
        return result;
    }

    @Override
    public List<Entidades> filtrarEntidades(Entidades entidade) {
        log.info("[FILTRAR ENTIDADE] - Selecionando processo de filtragem da entidade.");
        if (entidade.getId() != null) {
            log.info("[FILTRAR PONTO] - Iniciando filtro entidade por id: {}.", entidade.getId());
            var response = entidadeRepositoryOutputPort.buscarEntidadesPorId(entidade.getId());
            return List.of(response);
        }
        if (entidade.getNomeEntidade() != null && !entidade.getNomeEntidade().isBlank()) {
            log.info("[FILTRAR PONTO] - Iniciando filtro por nome da entidade: {}.", entidade.getNomeEntidade());
            var response = entidadeRepositoryOutputPort.buscarEntidadePorNome(entidade.getNomeEntidade());
            return List.of(response);
        }
        if (entidade.getFalange() != null && !entidade.getFalange().isBlank()) {
            log.info("[FILTRAR PONTO] - Iniciando filtro ponto por falange: .", entidade.getFalange());
            return entidadeRepositoryOutputPort.buscarEntidadesPorFalange(entidade.getFalange());
        }
        if (entidade.getLinhaEntidade() != null) {
            log.info("[FILTRAR PONTO] - Iniciando filtro por linha da entidade: {}.", entidade.getLinhaEntidade().getDescrition());
            return entidadeRepositoryOutputPort.buscarEntidadesPorLinha(entidade.getLinhaEntidade());
        }
        throw HttpClientErrorException.NotFound.create(HttpStatus.NOT_FOUND, "Not Found", null, null, null);
    }

    @Override
    public void deletarEntidade(Long entidadeId) {
        log.info("[DELETAR ENTIDADE] - Iniciando processo para deleção da entidade.");
        entidadeRepositoryOutputPort.deletarEntidade(entidadeId);
        messagingTemplate.convertAndSend("/topic/entidade", new SyncEvent<>("DELETE", entidadeId));
        log.info("[DELETAR ENTIDADE] - Busca de todas as entidades realizada com sucesso");
    }

    @Override
    public void atualizarEntidade(Long id, Entidades entidades) {
        log.info("[ATUALIZAR ENTIDADE] - Processo para atualizar a entidade id: {}, foi iniciado.", id);
        Entidades entidadeExistente = entidadeRepositoryOutputPort.buscarEntidadesPorId(id);

        if (entidades.getNomeEntidade() != null && !entidades.getNomeEntidade().isBlank()) {
            entidadeExistente.setNomeEntidade(entidades.getNomeEntidade());
        }
        if (entidades.getFalange() != null && !entidades.getFalange().isBlank()) {
            entidadeExistente.setFalange(entidades.getFalange());
        }
        if (entidades.getLinhaEntidade() != null) {
            entidadeExistente.setLinhaEntidade(entidades.getLinhaEntidade());
        }

        entidadeRepositoryOutputPort.incluirEntidade(entidadeExistente);
        messagingTemplate.convertAndSend("/topic/entidade", new SyncEvent<>("UPDATE", entidadeExistente));
        log.info("[ATUALIZAR ENTIDADE] - Entidade salva com sucesso!");}
}
