package com.api.batuque.adpater.input.gira.mapper;

import com.api.batuque.adpater.input.gira.dto.GiraFiltroRequest;
import com.api.batuque.adpater.input.gira.dto.GiraRequest;
import com.api.batuque.adpater.input.gira.dto.GiraResponse;
import com.api.batuque.adpater.input.gira.dto.ItemGiraRequest;
import com.api.batuque.adpater.input.gira.dto.ItemGiraResponse;
import com.api.batuque.adpater.input.playlist.dto.ItemPlaylistRequest;
import com.api.batuque.adpater.input.playlist.dto.ItemPlaylistResponse;
import com.api.batuque.adpater.input.playlist.dto.PlaylistFiltroRequest;
import com.api.batuque.adpater.input.playlist.dto.PlaylistResponse;
import com.api.batuque.domain.model.Gira;
import com.api.batuque.domain.model.GiraFiltro;
import com.api.batuque.domain.model.ItemGira;
import com.api.batuque.domain.model.ItemPlaylist;
import com.api.batuque.domain.model.Playlist;
import com.api.batuque.domain.model.PlaylistFiltro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GiraRequestMapper {

    // Entrada (POST)
    GiraRequest modelToDto(Gira playlist);

    Gira dtoToModel(GiraRequest playlistRequest);

    ItemGira itemDtoToModel(ItemGiraRequest itemRequest);

    GiraFiltro filtroToDomain(GiraFiltroRequest playlistFiltroRequest);

    // Saída (GET)
    GiraResponse modelToResponse(Gira playlist);

    ItemGiraResponse itemModelToResponse(ItemGira itemDomain);

    List<GiraResponse> modelListToDtolist(List<Gira> list);

    @Mapping(target = "ponto.id", source = "pontoId")
    ItemPlaylist itemDtoToModel(ItemPlaylistRequest itemRequest);

    PlaylistFiltro filtroToDomain(PlaylistFiltroRequest playlistFiltroRequest);

    @Mapping(target = "nome", source = "nomePlaylist")
    PlaylistResponse modelToResponse(Playlist playlist);

    @Mapping(target = "ponto", source = "ponto")
    @Mapping(target = "ordem", source = "ordem")
    ItemPlaylistResponse itemModelToResponse(ItemPlaylist itemDomain);

}
