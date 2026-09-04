package com.api.batuque.domain.usecase;

import com.api.batuque.domain.model.Playlist;
import com.api.batuque.domain.model.PlaylistFiltro;
import com.api.batuque.domain.model.PontoEntidade;
import com.api.batuque.domain.port.input.ControlePlaylistInputPort;
import com.api.batuque.domain.port.output.PlaylistRepositoryOutputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class ControlePlaylistUseCase implements ControlePlaylistInputPort {

    private final PlaylistRepositoryOutputPort playlistRepositoryOutputPort;

    @Override
    public void salvarPlaylist(Playlist playlist) {
        log.info("[SALVAR PLAYLIST] - Iniciando processo para salvar playlist: {}", playlist.getNomePlaylist());
        playlist.setNomePlaylist(playlist.getNomePlaylist().trim());
        playlistRepositoryOutputPort.salvarPlaylist(playlist);
        log.info("[SALVAR PLAYLIST] - Playlist salvo com sucesso.");
    }

    @Override
    public List<Playlist> buscarPlaylist() {
        log.info("[BUSCAR PLAYLIST] - Iniciando processo para buscar todas playlist");
        List<Playlist> result = playlistRepositoryOutputPort.buscarTodasPlaylist();
        log.info("[BUSCAR PLAYLIST] - Busca por todas as playlist finalizada.");
        return result;
    }

    @Override
    public List<Playlist> buscarPlaylistPorFiltro(PlaylistFiltro request) {
        log.info("[BUSCAR PLAYLIST] - Iniciando processo para buscar todas playlist");
        List<Playlist> result = playlistRepositoryOutputPort.buscarPorFiltro(request);
        log.info("[BUSCAR PLAYLIST] - Busca por todas as playlist finalizada.");
        return result;
    }

    @Override
    public void deletarPlaylistPorEntidade(Long id, String nomePonto, String nomeEntidade) {

    }

    @Override
    public void atualizarPlaylist(Long id, PontoEntidade pontoEntidade) {

    }
}
