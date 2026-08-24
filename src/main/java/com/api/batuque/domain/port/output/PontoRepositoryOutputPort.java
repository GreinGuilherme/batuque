package com.api.batuque.domain.port.output;

import com.api.batuque.domain.model.PontoEntidade;

import java.util.List;
import java.util.Optional;

public interface PontoRepositoryOutputPort {
    void incluirPonto(PontoEntidade pontoEntidade);
    List<PontoEntidade> buscarPontos();
    Optional<PontoEntidade> buscarPorId(Integer id);
    List<PontoEntidade> buscarPontosPorNomeEntidade(String nomeEntidade);
    void deletarPonto(Integer id, String nomePonto, String nomeEntidade);
    void atualizarPonto(PontoEntidade pontoEntidade);
}
