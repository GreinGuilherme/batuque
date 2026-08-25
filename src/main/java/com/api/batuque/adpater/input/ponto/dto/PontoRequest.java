package com.api.batuque.adpater.input.ponto.dto;

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
    private String pontoLetra;

    @NotNull(message = "Nome entidade é obrigatório")
    private String nomeEntidade;

    @NotNull(message = "ID da entidade é obrigatório")
    private Integer entidadeId;
}
