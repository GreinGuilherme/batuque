package com.api.batuque.adpater.output.database.giraJpa;

import com.api.batuque.adpater.output.database.giraJpa.entity.GiraEntity;
import com.api.batuque.adpater.output.database.giraJpa.entity.GiraPlaylistEntity;
import com.api.batuque.adpater.output.database.giraJpa.entity.GiraPlaylistIdEntity;
import com.api.batuque.adpater.output.database.giraJpa.mapper.GiraEntityMapper;
import com.api.batuque.adpater.output.database.playlistJpa.entity.PlaylistEntity;
import com.api.batuque.domain.model.Gira;
import com.api.batuque.domain.model.GiraFiltro;
import com.api.batuque.domain.model.ItemGira;
import com.api.batuque.domain.port.output.GiraRepositoryOutputPort;
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
public class GiraAdapter implements GiraRepositoryOutputPort {

    private final GiraJpaRepository giraJpaRepository;
    private final GiraEntityMapper giraEntityMapper;

    @Override
    @Transactional
    public Gira salvarGira(Gira gira) {
        log.info("[SALVAR GIRA] - Persistindo gira: {}", gira.getNomeGira());
        GiraEntity entity = giraEntityMapper.modelToDto(gira);

        if (entity.getPlaylist() != null && gira.getPlaylist() != null) {
            for (int i = 0; i < entity.getPlaylist().size(); i++) {
                GiraPlaylistEntity itemEntity = entity.getPlaylist().get(i);
                ItemGira itemModel = gira.getPlaylist().get(i);

                itemEntity.setGira(entity);

                if (itemEntity.getId() == null) {
                    itemEntity.setId(new GiraPlaylistIdEntity());
                }

                PlaylistEntity playlistRef = new PlaylistEntity();
                playlistRef.setId(itemModel.getPlaylistId());
                itemEntity.setPlaylist(playlistRef);
                itemEntity.getId().setPlaylistId(itemModel.getPlaylistId());
            }
        }

        GiraEntity saved = giraJpaRepository.save(entity);
        return giraEntityMapper.entityToModel(saved);
    }

    @Override
    public List<Gira> buscarTodasGira() {
        log.info("[BUSCAR GIRA] - Buscando todas as giras");
        return giraJpaRepository.findAllWithPlaylist().stream()
                .map(giraEntityMapper::entityToModel)
                .toList();
    }

    @Override
    public List<Gira> buscarPorFiltro(GiraFiltro request) {
        log.info("[FILTRAR GIRA] - Persisindo na busca de gira...");
        Specification<GiraEntity> spec = GiraSpecifications.comFiltro(request);
        List<GiraEntity> entities = giraJpaRepository.findAll(spec);
        return entities.stream()
                .map(giraEntityMapper::entityToModel)
                .toList();
    }

    @Override
    @Transactional
    public void deletarGira(Long id) {
        log.info("[DELETAR GIRA] - Deletando gira com ID: {}", id);
        if (!giraJpaRepository.existsById(id)) {
            throw new EntityNotFoundException("Gira não encontrada para o ID: " + id);
        }
        giraJpaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Gira atualizarGira(Long id, Gira giraAtualizada) {
        log.info("[ATUALIZAR GIRA] - Atualizando gira com ID: {}", id);
        GiraEntity entity = giraJpaRepository.findByIdWithPlaylist(id).orElseThrow(() -> new EntityNotFoundException("Gira não encontrada: " + id));;

        // Atualiza nome se fornecido
        if (giraAtualizada.getNomeGira() != null && !giraAtualizada.getNomeGira().isBlank()) {
            entity.setNomeGira(giraAtualizada.getNomeGira().trim());
        }

        // Se vier uma nova lista de pontos, sincroniza
        if (giraAtualizada.getPlaylist() != null) {
            entity.getPlaylist().clear(); // O orphanRemoval deleta do banco os itens desvinculados

            List<GiraPlaylistEntity> novosItens = giraAtualizada.getPlaylist().stream()
                    .map(item -> {
                        GiraPlaylistEntity pontoEntity = new GiraPlaylistEntity();
                        pontoEntity.setGira(entity);
                        pontoEntity.setOrdem(item.getOrdem());

                        PlaylistEntity pontoRef = new PlaylistEntity();
                        pontoRef.setId(item.getPlaylistId());
                        pontoEntity.setPlaylist(pontoRef);

                        GiraPlaylistIdEntity idComposto = new GiraPlaylistIdEntity(entity.getId(), item.getPlaylistId());
                        pontoEntity.setId(idComposto);
                        return pontoEntity;
                    }).toList();

            entity.getPlaylist().addAll(novosItens); // O cascade salva os novos
        }

        GiraEntity giraSave = giraJpaRepository.save(entity);
        giraSave.setId(id);
        Gira response = giraEntityMapper.entityToModel(giraSave);
        return response;
    }
}