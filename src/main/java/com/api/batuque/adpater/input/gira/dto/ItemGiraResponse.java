package com.api.batuque.adpater.input.gira.dto;

import com.api.batuque.adpater.input.playlist.dto.PlaylistResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemGiraResponse {
    private Integer ordem;
    private PlaylistResponse playlist;
}