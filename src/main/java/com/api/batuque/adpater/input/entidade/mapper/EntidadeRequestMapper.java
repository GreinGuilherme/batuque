package com.api.batuque.adpater.input.entidade.mapper;

import com.api.batuque.adpater.input.entidade.dto.EntidadeFiltroRequest;
import com.api.batuque.adpater.input.entidade.dto.EntidadeRequest;
import com.api.batuque.adpater.input.entidade.dto.EntidadeResponse;
import com.api.batuque.domain.model.Entidades;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntidadeRequestMapper {

    EntidadeRequest modelToDto(Entidades entidades);
    Entidades dtoToModel(EntidadeRequest entidadeRequest);
    List<EntidadeResponse> modelListToDtolist(List<Entidades> etidadesEntityList);
    List<Entidades> dtoListtoModelList(List<EntidadeResponse> entidadesList);
    Entidades dtoFiltroToModel(EntidadeFiltroRequest entidadeFiltroRequest);
    List<EntidadeResponse> modelToDtoFiltroList(List<Entidades> entidades);
}
