package com.api.batuque.adpater.input.gira.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemGiraRequest {

    @NotNull(message = "O ID da playlist é obrigatório")
    private Long playlistId;

    @NotNull(message = "A ordem do playlist é obrigatória")
    @Positive(message = "A ordem deve ser um número positivo")
    private Integer ordem;
}