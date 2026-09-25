package com.api.batuque.adpater.output.database.entidadeJpa.mapper;

import com.api.batuque.adpater.output.database.entidadeJpa.entity.EntidadeEntity;
import com.api.batuque.domain.model.Entidades;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntidadeEntityMapper {

    EntidadeEntity modelToDto(Entidades entidades);
    Entidades dtoToModel(EntidadeEntity entidadeRequest);
    List<EntidadeEntity> modelListToEntitylist(List<Entidades> etidadesEntityList);
    List<Entidades> entityListtoModelList(List<EntidadeEntity> entidadesList);
}
