package com.api.batuque.domain.usecase;

import com.api.batuque.domain.model.PontoEntidade;
import com.api.batuque.domain.port.input.ControlePontoInputPort;
import com.api.batuque.domain.port.output.EntidadeRepositoryOutputPort;
import com.api.batuque.domain.port.output.PontoRepositoryOutputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ControlePontoUseCase implements ControlePontoInputPort {

    private final PontoRepositoryOutputPort pontoRepositoryOutputPort;
    private final EntidadeRepositoryOutputPort entidadeRepositoryOutputPort;

    @Override
    public void salvarPonto(PontoEntidade controlePonto) {
        log.info("[SALVAR PONTO] - Verificar Entidade para salvar o ponto: ", controlePonto.getNomePonto());
        var result = entidadeRepositoryOutputPort.buscarEntidadePorNome(controlePonto.getNomeEntidade());
        log.info("[SALVAR PONTO] - Iniciando processo para salvar o : ", controlePonto.getNomePonto());
        controlePonto.setEntidade(result.getId());
        pontoRepositoryOutputPort.incluirPonto(controlePonto);
        log.info("[SALVAR PONTO] - Ponto salvo com sucesso!");
    }
}
