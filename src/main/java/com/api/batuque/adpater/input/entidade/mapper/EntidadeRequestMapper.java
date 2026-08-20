package com.api.batuque.adpater.input.entidade.mapper;

import com.api.batuque.adpater.input.entidade.dto.EntidadeRequest;
import com.api.batuque.domain.model.Entidades;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntidadeRequestMapper {

    EntidadeRequest modelToDto(Entidades entidades);
    Entidades dtoToModel(EntidadeRequest entidadeRequest);
}
