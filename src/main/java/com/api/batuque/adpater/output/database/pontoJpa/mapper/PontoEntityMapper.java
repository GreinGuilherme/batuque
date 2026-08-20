package com.api.batuque.adpater.output.database.pontoJpa.mapper;

import com.api.batuque.adpater.output.database.pontoJpa.entity.ControlePontoEntity;
import com.api.batuque.domain.model.Entidades;
import com.api.batuque.domain.model.PontoEntidade;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PontoEntityMapper {

    ControlePontoEntity modelToDto(PontoEntidade entidades);
    PontoEntidade dtoToModel(ControlePontoEntity entidadeRequest);
    List<ControlePontoEntity> modelListToEntitylist(List<PontoEntidade> etidadesEntityList);
    List<Entidades> entityListtoModelList(List<ControlePontoEntity> entidadesList);
}
