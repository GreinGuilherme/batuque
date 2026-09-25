package com.api.batuque.adpater.output.database.playlistJpa.mapper;

import com.api.batuque.adpater.output.database.playlistJpa.entity.PlaylistEntity;
import com.api.batuque.adpater.output.database.playlistJpa.entity.PlaylistPontoEntity;
import com.api.batuque.adpater.output.database.pontoJpa.mapper.PontoEntityMapper;
import com.api.batuque.domain.model.ItemPlaylist;
import com.api.batuque.domain.model.Playlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PontoEntityMapper.class})
public interface PlaylistEntityMapper {


    PlaylistEntity modelToDto(Playlist playlist);

    @Mapping(target = "ponto.id", source = "ponto.id")
    @Mapping(target = "id.pontoId", source = "ponto.id")
    PlaylistPontoEntity itemModelToEntity(ItemPlaylist itemPlaylist);

    Playlist dtoToModel(PlaylistEntity playlistEntity);
    Playlist entityToModel(PlaylistEntity saved);
    ItemPlaylist itemEntityToItemModel(PlaylistPontoEntity itemEntity);
}
