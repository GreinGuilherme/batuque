package com.api.batuque.domain.model;

import com.api.batuque.domain.enums.LinhaEntidadeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PontoEntidade {
    private Long id;
    private String nomePonto;
    private String pontoLetra;
    private String audioUrl;
    private String nomeEntidade;
    private Long entidadeId;           // FK da tabela 'entidade'
    private LinhaEntidadeEnum linhaEntidade; // Linha (EXU, CABOCLO, etc.)
}
