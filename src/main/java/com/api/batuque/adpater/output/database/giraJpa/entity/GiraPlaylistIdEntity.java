package com.api.batuque.adpater.output.database.giraJpa.entity;

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
public class GiraPlaylistIdEntity implements Serializable {
    @Column(name = "gira_id")
    private Long giraId;

    @Column(name = "playlist_id")
    private Long playlistId;
}
