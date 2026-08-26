package com.api.batuque.adpater.input.ponto.dto;

import com.api.batuque.domain.enums.LinhaEntidadeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PontoFiltroRequest {
    private Long id;
    private String nomePonto;
    private String pontoLetra;
    private String audioUrl;
    private String nomeEntidade;
    private Long entidadeId;
    private LinhaEntidadeEnum linhaEntidade;
}
