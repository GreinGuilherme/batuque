package com.api.batuque.adpater.input.entidade.dto;

import com.api.batuque.domain.enums.TipoEntidadeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EntidadeResponse {

    private int id;
    private String nomeEntidade;
    private TipoEntidadeEnum entidade;
}
