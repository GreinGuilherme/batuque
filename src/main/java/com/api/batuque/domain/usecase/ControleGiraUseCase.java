package com.api.batuque.domain.usecase;

import com.api.batuque.domain.model.Gira;
import com.api.batuque.domain.model.GiraFiltro;
import com.api.batuque.domain.port.input.ControleGiraInputPort;
import com.api.batuque.domain.port.output.GiraRepositoryOutputPort;
import com.api.batuque.domain.utils.SyncEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class ControleGiraUseCase implements ControleGiraInputPort {

    private final GiraRepositoryOutputPort giraRepositoryOutputPort;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void salvarGira(Gira gira) {
        log.info("[SALVAR GIRA] - Iniciando processo para salvar gira: {}", gira.getNomeGira());
        gira.setNomeGira(gira.getNomeGira().trim());
        Gira giraSaved = giraRepositoryOutputPort.salvarGira(gira);
        messagingTemplate.convertAndSend("/topic/gira", new SyncEvent<>("CREATE", giraSaved));
        log.info("[SALVAR GIRA] - Gira salvo com sucesso.");
    }

    @Override
    public List<Gira> buscarGira() {
        log.info("[BUSCAR GIRA] - Iniciando processo para buscar todas gira");
        List<Gira> result = giraRepositoryOutputPort.buscarTodasGira();
        log.info("[BUSCAR GIRA] - Busca por todas as gira finalizada.");
        return result;
    }

    @Override
    public List<Gira> buscarGiraPorFiltro(GiraFiltro request) {
        log.info("[BUSCAR GIRA] - Iniciando processo para buscar todas gira");
        List<Gira> result = giraRepositoryOutputPort.buscarPorFiltro(request);
        log.info("[BUSCAR GIRA] - Busca por todas as gira finalizada.");
        return result;
    }

    @Override
    public void deletarGiraPorId(Long id, String nomePonto, String nomeEntidade) {
        log.info("[DELETAR GIRA] - Iniciando processo para deletar gira: {} da entidade: {}.", nomePonto, nomeEntidade);
        giraRepositoryOutputPort.deletarGira(id);
        messagingTemplate.convertAndSend("/topic/gira", new SyncEvent<>("DELETE", id));
        log.info("[DELETAR GIRA] - Processo para deletar gira: {} da entidade: {}, foi concluido.", nomePonto, nomeEntidade);
    }

    @Override
    public void atualizarGira(Long id, Gira request) {
        log.info("[ATUALIZAR GIRA] - Iniciando processo para atualizar gira: {}.", request.getNomeGira());
        Gira giraSaved= giraRepositoryOutputPort.atualizarGira(id, request);
        messagingTemplate.convertAndSend("/topic/gira", new SyncEvent<>("UPDATE", giraSaved));
        log.info("[ATUALIZAR GIRA] - Processo para atualizar gira: {}, foi concluido.", giraSaved.getNomeGira());
    }
}
