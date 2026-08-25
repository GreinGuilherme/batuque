package com.api.batuque.domain.port.input;

import com.api.batuque.domain.model.Entidades;

import java.util.List;

public interface ControleEntidadeInputPort {
    void salvarEntidade (Entidades entidades);
    List<Entidades> buscarEntidades ();
    List<Entidades> filtrarEntidades (Entidades entidade);
    void deletarEntidade(Integer id);
}
