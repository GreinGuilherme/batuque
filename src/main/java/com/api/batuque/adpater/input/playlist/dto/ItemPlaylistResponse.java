package com.api.batuque.adpater.input.playlist.dto;

import com.api.batuque.adpater.input.ponto.dto.PontoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPlaylistResponse {
    private Integer ordem;
    private PontoResponse ponto;
}