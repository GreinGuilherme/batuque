package com.api.batuque.domain.port.output;

import com.api.batuque.domain.model.Gira;
import com.api.batuque.domain.model.GiraFiltro;

import java.util.List;

public interface GiraRepositoryOutputPort {
    Gira salvarGira(Gira gira);
    List<Gira> buscarTodasGira();
    List<Gira> buscarPorFiltro(GiraFiltro filtro);
    void deletarGira(Long id);
    void atualizarGira(Long id, Gira giraAtualizada);
}