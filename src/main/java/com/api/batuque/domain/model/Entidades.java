package com.api.batuque.domain.model;

import com.api.batuque.domain.enums.FalangeEntidadeEnum;
import com.api.batuque.domain.enums.TipoEntidadeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Entidades {
    private Integer id;
    private String nomeEntidade;
    private FalangeEntidadeEnum falangeEntidade;
    private TipoEntidadeEnum entidade;
}
