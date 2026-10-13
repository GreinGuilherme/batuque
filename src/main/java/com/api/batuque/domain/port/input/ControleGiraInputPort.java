package com.api.batuque.domain.port.input;

import com.api.batuque.domain.model.Gira;
import com.api.batuque.domain.model.GiraFiltro;

import java.util.List;

public interface ControleGiraInputPort {
    void salvarGira(Gira gira);
    List<Gira> buscarGira();
    List<Gira> buscarGiraPorFiltro(GiraFiltro request);
    void deletarGiraPorId(Long id, String nomePonto, String nomeEntidade);
    void atualizarGira(Long id, Gira request);
}
