package com.api.batuque.domain.usecase;

import com.api.batuque.adpater.output.database.entidadeJpa.EntidadeRepository;
import com.api.batuque.domain.model.Entidades;
import com.api.batuque.domain.port.input.ControleEntidadeInputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ControleEntidadeUseCase implements ControleEntidadeInputPort {

    private final EntidadeRepository entidadeRepository;

    @Override
    public void salvarEntidade(Entidades entidade) {
        log.info("[SALVAR ENTIDADE] - Iniciando processo para salvar entidade: ", entidade.getNomeEntidade());
        entidadeRepository.incluirEntidade(entidade.getNomeEntidade(), entidade.getEntidade().toString());
    }
}
