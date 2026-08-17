package com.api.batuque.adpater.output.database.pontoJpa;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PontoAdapter implements PontoRepository{


    @Override
    @Transactional
    public Integer incluirPonto(String nomePonto, String ponto, String entidade) {
        log.info("[SALVAR PONTO] - Iniciando processo para salvar o ponto");

        return 0;
    }
}
