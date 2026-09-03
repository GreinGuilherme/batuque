package com.api.batuque.adpater.input.playlist.mapper;

import com.api.batuque.adpater.input.playlist.dto.ItemPlaylistRequest;
import com.api.batuque.adpater.input.playlist.dto.PlaylistRequest;
import com.api.batuque.adpater.input.playlist.dto.PlaylistResponse;
import com.api.batuque.domain.model.ItemPlaylist;
import com.api.batuque.domain.model.Playlist;
import com.api.batuque.domain.model.PontoEntidade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistRequestMapper {

    PlaylistRequest modelToDto(Playlist playlist);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    Playlist dtoToModel(PlaylistRequest playlistRequest);

    // 2. Converte cada elemento da lista: popula ponto.id com pontoId
    @Mapping(target = "ponto.id", source = "pontoId")
    ItemPlaylist itemDtoToModel(ItemPlaylistRequest itemRequest);


    List<PlaylistResponse> modelListToDtolist(List<Playlist> List);
    List<PontoEntidade> dtoListtoModelList(List<PlaylistResponse> playlistResponseList);
}
