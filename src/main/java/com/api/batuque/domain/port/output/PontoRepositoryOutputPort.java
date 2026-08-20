package com.api.batuque.domain.port.output;

import com.api.batuque.domain.model.PontoEntidade;

public interface PontoRepositoryOutputPort {
    void incluirPonto(PontoEntidade pontoEntidade);
}
