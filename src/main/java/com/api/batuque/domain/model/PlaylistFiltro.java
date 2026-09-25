package com.api.batuque.domain.model;

import com.api.batuque.domain.enums.LinhaEntidadeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PlaylistFiltro {
    private Long playlistId;
    private String playlistNome;
    private Long entidadeId;
    private Long pontoId;
    private LinhaEntidadeEnum linhaEntidade;
    private String nomePonto;
    private String nomeEntidade;
    private String pontoLetra;
}
