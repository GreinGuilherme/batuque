package com.api.batuque.domain.port.output;

import com.api.batuque.domain.model.Playlist;
import com.api.batuque.domain.model.PlaylistFiltro;

import java.util.List;

public interface PlaylistRepositoryOutputPort {
    Playlist salvarPlaylist(Playlist playlist);
    List<Playlist> buscarTodasPlaylist();
    List<Playlist> buscarPorFiltro(PlaylistFiltro playlistId);
    void deletarPlaylist(Long id);
    void atualizarPlaylist(Long id, Playlist playlist);
}
