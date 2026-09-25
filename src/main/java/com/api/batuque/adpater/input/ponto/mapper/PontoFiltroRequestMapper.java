package com.api.batuque.adpater.input.ponto.mapper;

import com.api.batuque.adpater.input.ponto.dto.PontoFiltroRequest;
import com.api.batuque.domain.model.PontoEntidade;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PontoFiltroRequestMapper {

    PontoFiltroRequest modelToDto(PontoEntidade pontoEntidade);
    PontoEntidade dtoToModel(PontoFiltroRequest pontoFiltroRequest);
}
