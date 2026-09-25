package com.api.batuque.domain.usecase;

import com.api.batuque.domain.model.Playlist;
import com.api.batuque.domain.model.PlaylistFiltro;
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
    public void deletarPlaylistPorId(Long id, String nomePonto, String nomeEntidade) {
        log.info("[DELETAR PLAYLIST] - Iniciando processo para deletar playlist: {} da entidade: {}.", nomePonto, nomeEntidade);
        playlistRepositoryOutputPort.deletarPlaylist(id);
        log.info("[DELETAR PLAYLIST] - Processo para deletar playlist: {} da entidade: {}, foi concluido.", nomePonto, nomeEntidade);
    }

    @Override
    public void atualizarPlaylist(Long id, Playlist request) {
        log.info("[ATUALIZAR PLAYLIST] - Iniciando processo para atualizar playlist: {}.", request.getNomePlaylist());
        playlistRepositoryOutputPort.atualizarPlaylist(id, request);
        log.info("[ATUALIZAR PLAYLIST] - Processo para atualizar playlist: {}, foi concluido.", request.getNomePlaylist());
    }
}
