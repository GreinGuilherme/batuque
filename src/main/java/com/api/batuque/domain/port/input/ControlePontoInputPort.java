package com.api.batuque.domain.port.input;

import com.api.batuque.domain.model.PontoEntidade;

import java.util.List;

public interface ControlePontoInputPort {
    void salvarPonto(PontoEntidade controlePonto);
    List<PontoEntidade> buscarPontos();
    List<PontoEntidade> buscarPontosPorFiltro(PontoEntidade request);
    void deletarPontoPorEntidade(Integer id, String nomePonto, String nomeEntidade);
    void atualizarPonto(Integer id, PontoEntidade pontoEntidade);
}
