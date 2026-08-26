package com.api.batuque.adpater.input.entidade.dto;

import com.api.batuque.domain.enums.LinhaEntidadeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EntidadeResponse {

    private Long id;
    private String nomeEntidade;
    private String falange;
    private LinhaEntidadeEnum linhaEntidade;
}
