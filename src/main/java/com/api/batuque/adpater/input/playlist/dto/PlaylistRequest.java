package com.api.batuque.adpater.input.playlist.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PlaylistRequest {

    @NotBlank(message = "O nome da playlist é obrigatório")
    @Size(max = 100, message = "Máximo de 100 caracteres")
    private String nomePlaylist;

    @NotEmpty(message = "A playlist deve conter ao menos um ponto")
    @Valid
    private List<ItemPlaylistRequest> pontos;
}