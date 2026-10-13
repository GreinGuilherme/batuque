package com.api.batuque.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemGira {

    private Long playlistId;
    private Playlist playlist;
    private Integer ordem;
}