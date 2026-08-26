package com.api.batuque.adpater.output.database.playlistJpa.mapper;

import com.api.batuque.adpater.output.database.playlistJpa.entity.PlaylistEntity;
import com.api.batuque.domain.model.Playlist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaylistEntityMapper {


    PlaylistEntity modelToDto(Playlist playlist);
    Playlist dtoToModel(PlaylistEntity playlistEntity);
    Playlist entityToModel(PlaylistEntity saved);
}
