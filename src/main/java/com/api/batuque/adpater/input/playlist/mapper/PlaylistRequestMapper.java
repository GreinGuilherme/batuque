package com.api.batuque.adpater.input.playlist.mapper;

import com.api.batuque.adpater.input.playlist.dto.PlaylistRequest;
import com.api.batuque.adpater.input.playlist.dto.PlaylistResponse;
import com.api.batuque.domain.model.Playlist;
import com.api.batuque.domain.model.PontoEntidade;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistRequestMapper {

    PlaylistRequest modelToDto(Playlist playlist);
    Playlist dtoToModel(PlaylistRequest playlistRequest);
    List<PlaylistResponse> modelListToDtolist(List<Playlist> List);
    List<PontoEntidade> dtoListtoModelList(List<PlaylistResponse> playlistResponseList);
}
