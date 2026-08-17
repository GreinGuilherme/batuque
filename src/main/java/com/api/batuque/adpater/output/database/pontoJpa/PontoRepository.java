package com.api.batuque.adpater.output.database.pontoJpa;

public interface PontoRepository {
    Integer incluirPonto(String nomePonto, String ponto, String entidade);
}
