package com.api.batuque.domain.usecase;

import com.api.batuque.domain.model.PontoEntidade;
import com.api.batuque.domain.port.input.ControlePontoInputPort;
import com.api.batuque.domain.port.output.EntidadeRepositoryOutputPort;
import com.api.batuque.domain.port.output.PontoRepositoryOutputPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class ControlePontoUseCase implements ControlePontoInputPort {

    private final PontoRepositoryOutputPort pontoRepositoryOutputPort;
    private final EntidadeRepositoryOutputPort entidadeRepositoryOutputPort;

    @Override
    public void salvarPonto(PontoEntidade controlePonto) {
        log.info("[SALVAR PONTO] - Verificar Entidade para salvar o ponto: ", controlePonto.getNomePonto());
        var entidade = entidadeRepositoryOutputPort.buscarEntidadesPorId(controlePonto.getEntidadeId());
        log.info("[SALVAR PONTO] - Iniciando processo para salvar o : ", controlePonto.getNomePonto());
        controlePonto.setNomeEntidade(entidade.getNomeEntidade());
        controlePonto.setEntidadeId(entidade.getId());
        controlePonto.setLinhaEntidade(entidade.getLinhaEntidade());
        pontoRepositoryOutputPort.incluirPonto(controlePonto);
        log.info("[SALVAR PONTO] - Ponto salvo com sucesso!");
    }

    @Override
    public List<PontoEntidade> buscarPontos() {
        log.info("[BUSCAR PONTO] - Iniciando processo para buscar todos os pontos.");
        List<PontoEntidade> result = pontoRepositoryOutputPort.buscarPontos();
        log.info("[BUSCAR PONTO] - Busca por todos os pontos completa.");
        return result;
    }

    @Override
    public List<PontoEntidade> buscarPontosPorFiltro(PontoEntidade request) {
        log.info("[BUSCAR PONTO] - Iniciando busca por filtro!");
        List<PontoEntidade> result = pontoRepositoryOutputPort.buscarPontosPorFiltro(request);
        log.info("[BUSCAR PONTO] - Iniciando busca ponto por id: {}.", request.getId());
        return result;
    }

    @Override
    public void deletarPontoPorEntidade(Long id, String nomePonto, String nomeEntidade) {
        log.info("[DELETAR PONTO] - Iniciando processo para deletar o ponto: {} da entidade: {}", nomePonto, nomeEntidade);
        pontoRepositoryOutputPort.deletarPonto(id, nomePonto, nomeEntidade);
        log.info("[DELETAR PONTO] - Processo de deleção completo!");
    }

    @Override
    public void atualizarPonto(Long id, PontoEntidade pontoEntidade) {
        log.info("[ATUALIZAR PONTO] - Processo para atualizar o ponto: {}, foi iniciado.", pontoEntidade.getNomePonto());
        PontoEntidade pontoExistente = pontoRepositoryOutputPort.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Ponto não encontrado para o ID: " + id));

        if (pontoEntidade.getNomePonto() != null && !pontoEntidade.getNomePonto().isBlank()) {
            pontoExistente.setNomePonto(pontoEntidade.getNomePonto());
        }
        if (pontoEntidade.getPontoLetra() != null && !pontoEntidade.getPontoLetra().isBlank()) {
            pontoExistente.setPontoLetra(pontoEntidade.getPontoLetra());
        }
        if (pontoEntidade.getAudioUrl() != null && !pontoEntidade.getAudioUrl().isBlank()) {
            pontoExistente.setAudioUrl(pontoEntidade.getAudioUrl());
        }
        if (pontoEntidade.getNomeEntidade() != null && !pontoEntidade.getNomeEntidade().isBlank()) {
            var entidade = entidadeRepositoryOutputPort.buscarEntidadePorNome(pontoEntidade.getNomeEntidade());
            pontoExistente.setEntidadeId(entidade.getId());
            pontoExistente.setNomeEntidade(pontoEntidade.getNomeEntidade());
        }

        pontoRepositoryOutputPort.atualizarPonto(pontoExistente);
        log.info("[ATUALIZAR PONTO] - Ponto salvo com sucesso!");
    }
}
