package com.api.batuque.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {
    private Long id;
    private String nomePlaylist;
    private LocalDateTime dataCriacao;
    private List<ItemPlaylist> pontos;
}
