package com.api.batuque.adpater.output.database.playlistJpa.entity;

import com.api.batuque.adpater.output.database.pontoJpa.entity.ControlePontoEntity;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "playlist_ponto")
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistPontoEntity {

    @EmbeddedId
    private PlaylistPontoIdEntity id = new PlaylistPontoIdEntity();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("playlistId") // Vincula este relacionamento ao atributo playlistId do EmbeddedId
    @JoinColumn(name = "playlist_id")
    private PlaylistEntity playlist;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pontoId") // Vincula ao atributo pontoId do EmbeddedId
    @JoinColumn(name = "ponto_id")
    private ControlePontoEntity ponto;

    @Column(name = "ordem", nullable = false)
    private Integer ordem;
}
