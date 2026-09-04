package com.api.batuque.adpater.input.playlist.mapper;

import com.api.batuque.adpater.input.playlist.dto.ItemPlaylistRequest;
import com.api.batuque.adpater.input.playlist.dto.ItemPlaylistResponse;
import com.api.batuque.adpater.input.playlist.dto.PlaylistFiltroRequest;
import com.api.batuque.adpater.input.playlist.dto.PlaylistRequest;
import com.api.batuque.adpater.input.playlist.dto.PlaylistResponse;
import com.api.batuque.domain.model.ItemPlaylist;
import com.api.batuque.domain.model.Playlist;
import com.api.batuque.domain.model.PlaylistFiltro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistRequestMapper {

    // Entrada (POST)
    PlaylistRequest modelToDto(Playlist playlist);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    Playlist dtoToModel(PlaylistRequest playlistRequest);

    @Mapping(target = "ponto.id", source = "pontoId")
    ItemPlaylist itemDtoToModel(ItemPlaylistRequest itemRequest);

    PlaylistFiltro filtroToDomain(PlaylistFiltroRequest playlistFiltroRequest);

    // Saída (GET)
    @Mapping(target = "nome", source = "nomePlaylist")
    PlaylistResponse modelToResponse(Playlist playlist);

    @Mapping(target = "ponto", source = "ponto")
    @Mapping(target = "ordem", source = "ordem")
    ItemPlaylistResponse itemModelToResponse(ItemPlaylist itemDomain);

    List<PlaylistResponse> modelListToDtolist(List<Playlist> list);
}
