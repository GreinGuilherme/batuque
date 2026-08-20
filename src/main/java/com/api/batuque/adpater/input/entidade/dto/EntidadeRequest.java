package com.api.batuque.adpater.input.entidade.dto;

import com.api.batuque.domain.enums.TipoEntidadeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EntidadeRequest {

    @NotNull(message = "Nome da entidade é obrigatório")
    private String nomeEntidade;

    @NotNull(message = "Qual é a entidade é obrigatório")
    private TipoEntidadeEnum entidade;
}
