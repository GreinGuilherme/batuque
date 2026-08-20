package com.api.batuque.adpater.output.database.pontoJpa;

import com.api.batuque.adpater.output.database.pontoJpa.entity.ControlePontoEntity;
import com.api.batuque.adpater.output.database.pontoJpa.mapper.PontoEntityMapper;
import com.api.batuque.domain.model.PontoEntidade;
import com.api.batuque.domain.port.output.PontoRepositoryOutputPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PontoAdapter implements PontoRepositoryOutputPort {

    private final PontoRepository pontoRepository;
    private final PontoEntityMapper pontoEntityMapper;

    @Transactional
    public void incluirPonto(PontoEntidade pontoEntidade) {
        log.info("[SALVAR PONTO] - Iniciando processo para salvar o ponto");
        ControlePontoEntity ponto = pontoEntityMapper.modelToDto(pontoEntidade);
        pontoRepository.save(ponto);
        log.info("[SALVAR PONTO] - Ponto foi salvo com sucesso!");
    }
}
