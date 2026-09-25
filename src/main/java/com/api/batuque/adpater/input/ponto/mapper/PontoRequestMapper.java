package com.api.batuque.adpater.input.ponto.mapper;

import com.api.batuque.adpater.input.ponto.dto.PontoRequest;
import com.api.batuque.adpater.input.ponto.dto.PontoResponse;
import com.api.batuque.domain.model.PontoEntidade;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PontoRequestMapper {

    PontoRequest modelToDto(PontoEntidade pontoEntidade);
    PontoEntidade dtoToModel(PontoRequest pontoRequest);
    List<PontoResponse> modelListToDtolist(List<PontoEntidade> List);
    List<PontoEntidade> dtoListtoModelList(List<PontoResponse> pontoResponseListList);
}
