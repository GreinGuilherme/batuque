package com.api.batuque.domain.usecase;

import com.api.batuque.domain.model.Entidades;
import com.api.batuque.domain.port.input.ControleEntidadeInputPort;
import com.api.batuque.domain.port.output.EntidadeRepositoryOutputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class ControleEntidadeUseCase implements ControleEntidadeInputPort {

    private final EntidadeRepositoryOutputPort entidadeRepositoryOutputPort;

    @Override
    public void salvarEntidade(Entidades entidade) {
        log.info("[SALVAR ENTIDADE] - Iniciando processo para salvar entidade: ", entidade.getNomeEntidade());
        entidadeRepositoryOutputPort.incluirEntidade(entidade);
        log.info("[SALVAR ENTIDADE] - Entidade salvo com sucesso.");
    }

    @Override
    public List<Entidades> buscarEntidades() {
        log.info("[BUSCAR ENTIDADE] - Iniciando processo para buscar todas entidades.");
        List<Entidades> result = entidadeRepositoryOutputPort.buscarEntidades();
        log.info("[BUSCAR ENTIDADE] - Busca por toodas as entidades completa.");
        return result;
    }
}
