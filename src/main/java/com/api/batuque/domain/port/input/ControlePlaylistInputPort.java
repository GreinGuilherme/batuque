package com.api.batuque.domain.port.input;

import com.api.batuque.domain.model.Playlist;
import com.api.batuque.domain.model.PontoEntidade;

import java.util.List;

public interface ControlePlaylistInputPort {
    void salvarPlaylist(Playlist playlist);
    List<Playlist> buscarPlaylist();
    List<PontoEntidade> buscarPlaylistPorFiltro(PontoEntidade request);
    void deletarPlaylistPorEntidade(Long id, String nomePonto, String nomeEntidade);
    void atualizarPlaylist(Long id, PontoEntidade pontoEntidade);
}
