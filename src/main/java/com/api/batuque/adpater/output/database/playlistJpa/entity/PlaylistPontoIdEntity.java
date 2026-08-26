package com.api.batuque.adpater.output.database.playlistJpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PlaylistPontoIdEntity implements Serializable {
    @Column(name = "playlist_id")
    private Long playlistId;

    @Column(name = "ponto_id")
    private Long pontoId;
}
