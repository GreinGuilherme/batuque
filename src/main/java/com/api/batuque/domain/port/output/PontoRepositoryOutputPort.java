package com.api.batuque.domain.port.output;

import com.api.batuque.domain.model.PontoEntidade;

import java.util.List;
import java.util.Optional;

public interface PontoRepositoryOutputPort {
    void incluirPonto(PontoEntidade pontoEntidade);
    List<PontoEntidade> buscarPontos();
    Optional<PontoEntidade> buscarPorId(Long id);
    List<PontoEntidade> buscarPontosPorFiltro(PontoEntidade pontoEntidade);
    void deletarPonto(Long id, String nomePonto, String nomeEntidade);
    void atualizarPonto(PontoEntidade pontoEntidade);
}
