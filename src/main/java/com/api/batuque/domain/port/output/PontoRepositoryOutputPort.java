package com.api.batuque.domain.port.output;

import com.api.batuque.domain.enums.LinhaEntidadeEnum;
import com.api.batuque.domain.model.PontoEntidade;

import java.util.List;
import java.util.Optional;

public interface PontoRepositoryOutputPort {
    void incluirPonto(PontoEntidade pontoEntidade);
    List<PontoEntidade> buscarPontos();
    Optional<PontoEntidade> buscarPorId(Long id);
    List<PontoEntidade> buscarPontosPorNomePontos(String nomePonto);
    List<PontoEntidade> buscarPontosPorPontoLetra(String pontoLetra);
    List<PontoEntidade> buscarPontosNomeEntidade(String nomeEntidade);
    List<PontoEntidade> buscarPontosPorLinhaEntidade(LinhaEntidadeEnum linhaEntidadeEnum);
    void deletarPonto(Long id, String nomePonto, String nomeEntidade);
    void atualizarPonto(PontoEntidade pontoEntidade);
}
