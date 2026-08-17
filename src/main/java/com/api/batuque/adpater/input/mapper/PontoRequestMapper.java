package com.api.batuque.adpater.input.mapper;

import com.api.batuque.adpater.input.dto.PontoRequest;
import com.api.batuque.domain.model.PontoEntidade;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PontoRequestMapper {

    PontoRequest modelToDto(PontoEntidade pontoEntidade);
    PontoEntidade dtoToModel(PontoRequest pontoRequest);
}
