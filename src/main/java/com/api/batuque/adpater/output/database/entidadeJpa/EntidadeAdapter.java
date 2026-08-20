package com.api.batuque.adpater.output.database.entidadeJpa;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Repository
@RequiredArgsConstructor
public class EntidadeAdapter implements EntidadeRepository {

    @Override
    @Transactional
    public Integer incluirEntidade(String nomePonto, String entidade) {
        log.info("[SALVAR PONTO] - Iniciando processo para salvar o ponto");

        return 0;
    }
}
