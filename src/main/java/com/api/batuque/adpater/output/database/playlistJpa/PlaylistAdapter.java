package com.api.batuque.adpater.output.database.playlistJpa;

import com.api.batuque.adpater.output.database.playlistJpa.entity.PlaylistEntity;
import com.api.batuque.adpater.output.database.playlistJpa.entity.PlaylistPontoEntity;
import com.api.batuque.adpater.output.database.playlistJpa.entity.PlaylistPontoIdEntity;
import com.api.batuque.adpater.output.database.playlistJpa.mapper.PlaylistEntityMapper;
import com.api.batuque.adpater.output.database.pontoJpa.entity.ControlePontoEntity;
import com.api.batuque.domain.model.Playlist;
import com.api.batuque.domain.model.PlaylistFiltro;
import com.api.batuque.domain.port.output.PlaylistRepositoryOutputPort;
import jakarta.persistence.EntityNotFoundException;
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
        log.info("[BUSCAR PLAYLIST] - Buscando todas as playlists");
        return playlistJpaRepository.findAllWithPontos().stream()
                .map(playlistEntityMapper::entityToModel)
                .toList();
    }

    @Override
    public List<Playlist> buscarPorFiltro(PlaylistFiltro request) {
        log.info("[FILTRAR PLAYLIST] - Persisindo na busca de playlist...");
        Specification<PlaylistEntity> spec = PlaylistSpecifications.comFiltro(request);
        List<PlaylistEntity> entities = playlistJpaRepository.findAll(spec);
        return entities.stream()
                .map(playlistEntityMapper::entityToModel)
                .toList();
    }

    @Override
    @Transactional
    public void deletarPlaylist(Long id) {
        log.info("[DELETAR PLAYLIST] - Deletando playlist com ID: {}", id);
        if (!playlistJpaRepository.existsById(id)) {
            throw new EntityNotFoundException("Playlist não encontrada para o ID: " + id);
        }
        playlistJpaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void atualizarPlaylist(Long id, Playlist playlistAtualizada) {
        log.info("[ATUALIZAR PLAYLIST] - Atualizando playlist com ID: {}", id);
        PlaylistEntity entity = playlistJpaRepository.findByIdWithPontos(id).orElseThrow(() -> new EntityNotFoundException("Playlist não encontrada: " + id));;

        // Atualiza nome se fornecido
        if (playlistAtualizada.getNomePlaylist() != null && !playlistAtualizada.getNomePlaylist().isBlank()) {
            entity.setNomePlaylist(playlistAtualizada.getNomePlaylist().trim());
        }

        // Se vier uma nova lista de pontos, sincroniza
        if (playlistAtualizada.getPontos() != null) {
            entity.getPontos().clear(); // O orphanRemoval deleta do banco os itens desvinculados

            List<PlaylistPontoEntity> novosItens = playlistAtualizada.getPontos().stream()
                    .map(item -> {
                        PlaylistPontoEntity pontoEntity = new PlaylistPontoEntity();
                        pontoEntity.setPlaylist(entity);
                        pontoEntity.setOrdem(item.getOrdem());

                        ControlePontoEntity pontoRef = new ControlePontoEntity();
                        pontoRef.setId(item.getPonto().getId());
                        pontoEntity.setPonto(pontoRef);

                        PlaylistPontoIdEntity idComposto = new PlaylistPontoIdEntity(entity.getId(), item.getPonto().getId());
                        pontoEntity.setId(idComposto);
                        return pontoEntity;
                    }).toList();

            entity.getPontos().addAll(novosItens); // O cascade salva os novos
        }

        PlaylistEntity saved = playlistJpaRepository.save(entity);
    }
}