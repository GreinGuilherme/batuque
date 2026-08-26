package com.api.batuque.adpater.input.playlist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PlaylistFiltroRequest {
    private Long id;
    private String nome;
    private LocalDateTime dataCriacao;
    private List<ItemPlaylistResponse> pontos;
}
