package com.api.batuque.adpater.output.database.giraJpa.entity;

import com.api.batuque.adpater.output.database.playlistJpa.entity.PlaylistEntity;
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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "gira_playlist")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class GiraPlaylistEntity {

    @EmbeddedId
    private GiraPlaylistIdEntity id = new GiraPlaylistIdEntity();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("giraId") // Vincula este relacionamento ao atributo playlistId do EmbeddedId
    @JoinColumn(name = "gira_id")
    private GiraEntity gira;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("playlistId") // Vincula ao atributo pontoId do EmbeddedId
    @JoinColumn(name = "playlist_id")
    private PlaylistEntity playlist;

    @Column(name = "ordem", nullable = false)
    private Integer ordem;
}
