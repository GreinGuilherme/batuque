package com.api.batuque.adpater.input.playlist.dto;

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
public class ItemPlaylistRequest {

    @NotNull(message = "O ID do ponto é obrigatório")
    private Long pontoId;

    @NotNull(message = "A ordem do ponto é obrigatória")
    @Positive(message = "A ordem deve ser um número positivo")
    private Integer ordem;
}