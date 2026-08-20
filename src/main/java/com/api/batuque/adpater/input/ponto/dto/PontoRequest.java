package com.api.batuque.adpater.input.ponto.dto;

import com.api.batuque.domain.enums.FalangeEntidadeEnum;
import com.api.batuque.domain.enums.TipoEntidadeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PontoRequest {

    @NotNull(message = "Nome do ponto é obrigatório")
    private String nomePonto;

    @NotNull(message = "Ponto é obrigatório")
    private String ponto;

    private FalangeEntidadeEnum falangeEntidade;

    @NotNull(message = "Qual é a entidade é obrigatório")
    private TipoEntidadeEnum entidade;
}
