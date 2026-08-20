package com.api.batuque.domain.model;

import com.api.batuque.domain.enums.TipoEntidadeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Entidades {
    private String nomeEntidade;
    private TipoEntidadeEnum entidade;
}
