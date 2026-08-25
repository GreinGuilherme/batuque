package com.api.batuque.adpater.input.ponto.dto;

import com.api.batuque.domain.enums.LinhaEntidadeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PontoResponse {

    private int id;
    private String nomePonto;
    private String pontoLetra;
    private String audioUrl;
    private String nomeEntidade;
    private Integer entidadeId;
    private LinhaEntidadeEnum linhaEntidade;
}
