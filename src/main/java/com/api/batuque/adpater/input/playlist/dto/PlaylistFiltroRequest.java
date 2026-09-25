package com.api.batuque.adpater.input.playlist.dto;

import com.api.batuque.domain.enums.LinhaEntidadeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PlaylistFiltroRequest {
    private Long playlistId;
    private String playlistNome;
    private Long entidadeId;
    private Long pontoId;
    private LinhaEntidadeEnum linhaEntidade;
    private String nomePonto;
    private String nomeEntidade;
    private String pontoLetra;
}
