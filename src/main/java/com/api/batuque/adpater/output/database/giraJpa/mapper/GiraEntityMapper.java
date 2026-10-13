package com.api.batuque.adpater.output.database.giraJpa.mapper;

import com.api.batuque.adpater.output.database.giraJpa.entity.GiraEntity;
import com.api.batuque.adpater.output.database.giraJpa.entity.GiraPlaylistEntity;
import com.api.batuque.adpater.output.database.pontoJpa.mapper.PontoEntityMapper;
import com.api.batuque.domain.model.Gira;
import com.api.batuque.domain.model.ItemGira;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PontoEntityMapper.class})
public interface GiraEntityMapper {


    GiraEntity modelToDto(Gira playlist);
    GiraPlaylistEntity itemModelToEntity(ItemGira itemGira);
    Gira entityToModel(GiraEntity saved);
    ItemGira itemEntityToItemModel(GiraPlaylistEntity itemEntity);

}
