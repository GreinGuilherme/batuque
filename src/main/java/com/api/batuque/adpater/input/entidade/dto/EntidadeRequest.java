package com.api.batuque.adpater.input.entidade.dto;

import com.api.batuque.domain.enums.LinhaEntidadeEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EntidadeRequest {

    @NotNull(message = "Nome da entidade é obrigatório")
    @Size(max = 50, message = "Máximo de 50 caracteres.")
    private String nomeEntidade;

    @Size(max = 50, message = "Máximo de 50 caracteres.")
    private String falange;

    @NotNull(message = "Qual é a entidade é obrigatório")
    private LinhaEntidadeEnum linhaEntidade;
}
