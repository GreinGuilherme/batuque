package com.api.batuque.adpater.output.database.pontoJpa.mapper;

import com.api.batuque.adpater.output.database.pontoJpa.entity.ControlePontoEntity;
import com.api.batuque.domain.model.PontoEntidade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PontoEntityMapper {

    @Mapping(target = "entidade.id", source = "entidade")
    ControlePontoEntity modelToDto(PontoEntidade entidades);

    @Mapping(target = "entidade", source = "entidade.id")
    @Mapping(target = "nomeEntidade", source = "entidade.nomeEntidade")
    PontoEntidade dtoToModel(ControlePontoEntity entidadeRequest);

    List<ControlePontoEntity> modelListToEntitylist(List<PontoEntidade> etidadesEntityList);
    List<PontoEntidade> entityListtoModelList(List<ControlePontoEntity> entidadesList);
}
