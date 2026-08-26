package com.api.batuque.domain.port.output;

import com.api.batuque.domain.model.Playlist;

import java.util.List;

public interface PlaylistRepositoryOutputPort {
    Playlist salvarPlaylist(Playlist playlist);
    List<Playlist> buscarTodasPlaylist();
//    Optional<Playlist> buscarPorId(Long id);
//    List<Playlist> buscarPorNome(String nome);
//    void deletarPlaylist(Long id);
//    boolean existePontoNaPlaylist(Long playlistId, Long pontoId);
}
