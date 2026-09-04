package com.api.batuque.adpater.output.database.playlistJpa;

import com.api.batuque.adpater.output.database.entidadeJpa.entity.EntidadeEntity;
import com.api.batuque.adpater.output.database.playlistJpa.entity.PlaylistEntity;
import com.api.batuque.adpater.output.database.playlistJpa.entity.PlaylistPontoEntity;
import com.api.batuque.adpater.output.database.pontoJpa.entity.ControlePontoEntity;
import com.api.batuque.domain.model.PlaylistFiltro;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PlaylistSpecifications {

    public static Specification<PlaylistEntity> comFiltro(PlaylistFiltro filtro) {
        return (root, query, cb) -> {
            query.distinct(true);
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getPlaylistId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getPlaylistId()));
            }

            if (filtro.getPlaylistNome() != null && !filtro.getPlaylistNome().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nomePlaylist")), "%" + filtro.getPlaylistNome().toLowerCase() + "%"));
            }

            // Joins para filtrar por dados dos pontos contidos na playlist
            boolean precisaJoinPonto = filtro.getPontoId() != null
                    || (filtro.getNomePonto() != null && !filtro.getNomePonto().isBlank())
                    || (filtro.getPontoLetra() != null && !filtro.getPontoLetra().isBlank())
                    || filtro.getEntidadeId() != null
                    || filtro.getLinhaEntidade() != null;

            if (precisaJoinPonto) {
                Join<PlaylistEntity, PlaylistPontoEntity> joinPlaylistPonto = root.join("pontos", JoinType.INNER);
                Join<PlaylistPontoEntity, ControlePontoEntity> joinPonto = joinPlaylistPonto.join("ponto", JoinType.INNER);

                if (filtro.getPontoId() != null) {
                    predicates.add(cb.equal(joinPonto.get("id"), filtro.getPontoId()));
                }
                if (filtro.getNomePonto() != null && !filtro.getNomePonto().isBlank()) {
                    predicates.add(cb.like(cb.lower(joinPonto.get("nomePonto")), "%" + filtro.getNomePonto().toLowerCase() + "%"));
                }
                if (filtro.getPontoLetra() != null && !filtro.getPontoLetra().isBlank()) {
                    predicates.add(cb.like(cb.lower(joinPonto.get("pontoLetra")), "%" + filtro.getPontoLetra().toLowerCase() + "%"));
                }

                if (filtro.getEntidadeId() != null || filtro.getLinhaEntidade() != null) {
                    Join<ControlePontoEntity, EntidadeEntity> joinEntidade = joinPonto.join("entidade", JoinType.INNER);

                    if (filtro.getEntidadeId() != null) {
                        predicates.add(cb.equal(joinEntidade.get("id"), filtro.getEntidadeId()));
                    }
                    if (filtro.getLinhaEntidade() != null) {
                        predicates.add(cb.equal(joinEntidade.get("linhaEntidade"), filtro.getLinhaEntidade()));
                    }
                }
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}