package com.api.batuque.domain.port.output;

import com.api.batuque.domain.enums.LinhaEntidadeEnum;
import com.api.batuque.domain.model.Entidades;

import java.util.List;

public interface EntidadeRepositoryOutputPort {
    void incluirEntidade(Entidades entidade);
    List<Entidades> buscarEntidades();
    Entidades buscarEntidadePorNome(String nome);
    Entidades buscarEntidadesPorId(Integer entidadeId);
    List<Entidades> buscarEntidadesPorFalange(String falange);
    List<Entidades> buscarEntidadesPorLinha(LinhaEntidadeEnum linhaEntidade);
    void deletarEntidade(Integer entidadeId);
}
