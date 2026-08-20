package com.api.batuque.domain.usecase;

import com.api.batuque.adpater.output.database.pontoJpa.PontoRepository;
import com.api.batuque.domain.model.PontoEntidade;
import com.api.batuque.domain.port.input.ControlePontoInputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ControlePontoUseCase implements ControlePontoInputPort {

    private final PontoRepository pontoRepository;

    @Override
    public void entradaPonto(PontoEntidade controlePonto) {
        log.info("[SALVAR PONTO] - Iniciando processo para salvar o : ", controlePonto.getNomePonto());
        pontoRepository.incluirPonto(controlePonto.getNomePonto(), controlePonto.getPonto(), controlePonto.getEntidade().toString());
    }
}
