package com.api.batuque.domain.usecase;

import com.api.batuque.adpater.output.database.pontoJpa.PontoRepository;
import com.api.batuque.domain.model.PontoEntidade;
import com.api.batuque.domain.port.input.ControlePontoInputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class ControlePontoUseCase implements ControlePontoInputPort {

    private final PontoRepository pontoRepository;

    @Override
    public void entradaPonto(PontoEntidade controlePonto) {
        log.info("[SALVAR PONTO] - Iniciando processo para salvar o ponto");
        pontoRepository.incluirPonto(controlePonto.getNomePonto(), controlePonto.getPonto(), controlePonto.getEntidade());
    }
}
