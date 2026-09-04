package com.api.batuque.adpater.output.database.playlistJpa;

import com.api.batuque.adpater.output.database.playlistJpa.entity.PlaylistEntity;
import com.api.batuque.adpater.output.database.playlistJpa.entity.PlaylistPontoIdEntity;
import com.api.batuque.adpater.output.database.playlistJpa.mapper.PlaylistEntityMapper;
import com.api.batuque.domain.model.Playlist;
import com.api.batuque.domain.model.PlaylistFiltro;
import com.api.batuque.domain.port.output.PlaylistRepositoryOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlaylistAdapter implements PlaylistRepositoryOutputPort {

    private final PlaylistJpaRepository playlistJpaRepository;
    private final PlaylistEntityMapper playlistEntityMapper;

    @Override
    @Transactional
    public Playlist salvarPlaylist(Playlist playlist) {
        log.info("[SALVAR PLAYLIST] - Persistindo playlist: {}", playlist.getNomePlaylist());
        PlaylistEntity entity = playlistEntityMapper.modelToDto(playlist);

        // Garante que o vínculo bidirecional da relação 1:N seja preenchido antes de salvar
        if (entity.getPontos() != null) {
            entity.getPontos().forEach(item -> {
                item.setPlaylist(entity);
                if (item.getId() == null) {
                    item.setId(new PlaylistPontoIdEntity());
                }
                if (item.getPonto() != null) {
                    item.getId().setPontoId(item.getPonto().getId());
                }
            });
        }

        PlaylistEntity saved = playlistJpaRepository.save(entity);
        return playlistEntityMapper.entityToModel(saved);
    }

    @Override
    public List<Playlist> buscarTodasPlaylist() {
        log.info("[PLAYLIST ADAPTER] - Buscando todas as playlists");
        return playlistJpaRepository.findAllWithPontos().stream()
                .map(playlistEntityMapper::entityToModel)
                .toList();
    }

    @Override
    public List<Playlist> buscarPorFiltro(PlaylistFiltro request) {
        log.info("[PLAYLIST ADAPTER] - Persisindo na busca de playlist...");
        Specification<PlaylistEntity> spec = PlaylistSpecifications.comFiltro(request);
        List<PlaylistEntity> entities = playlistJpaRepository.findAll(spec);
        return entities.stream()
                .map(playlistEntityMapper::entityToModel)
                .toList();
    }
//
//    @Override
//    @Transactional
//    public void deletarPlaylist(Long id) {
//        log.info("[PLAYLIST ADAPTER] - Deletando playlist com ID: {}", id);
//        playlistJpaRepository.deleteById(id);
//    }
//
//    @Override
//    public boolean existePontoNaPlaylist(Long playlistId, Long pontoId) {
//        return playlistJpaRepository.existsPontoInPlaylist(playlistId, pontoId);
//    }
}