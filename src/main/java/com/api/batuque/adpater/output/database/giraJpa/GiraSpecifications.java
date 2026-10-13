package com.api.batuque.adpater.output.database.giraJpa;

import com.api.batuque.adpater.output.database.entidadeJpa.entity.EntidadeEntity;
import com.api.batuque.adpater.output.database.giraJpa.entity.GiraEntity;
import com.api.batuque.adpater.output.database.giraJpa.entity.GiraPlaylistEntity;
import com.api.batuque.adpater.output.database.playlistJpa.entity.PlaylistEntity;
import com.api.batuque.adpater.output.database.playlistJpa.entity.PlaylistPontoEntity;
import com.api.batuque.adpater.output.database.pontoJpa.entity.ControlePontoEntity;
import com.api.batuque.domain.model.GiraFiltro;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GiraSpecifications {

    public static Specification<GiraEntity> comFiltro(GiraFiltro filtro) {
        return (root, query, cb) -> {
            query.distinct(true);
            List<Predicate> predicates = new ArrayList<>();

            // 1. Filtro Direto na Playlist (Ligação Direta com Gira)
            if (filtro.getPlaylistId() != null || (filtro.getPlaylistNome() != null && !filtro.getPlaylistNome().isBlank())) {
                Join<GiraEntity, GiraPlaylistEntity> joinGiraPlaylist = root.join("playlist", JoinType.INNER);
                Join<GiraPlaylistEntity, PlaylistEntity> joinPlaylist = joinGiraPlaylist.join("playlist", JoinType.INNER);

                if (filtro.getPlaylistId() != null) {
                    predicates.add(cb.equal(joinPlaylist.get("id"), filtro.getPlaylistId()));
                }

                if (filtro.getPlaylistNome() != null && !filtro.getPlaylistNome().isBlank()) {
                    predicates.add(cb.like(cb.lower(joinPlaylist.get("nomePlaylist")), "%" + filtro.getPlaylistNome().toLowerCase() + "%"));
                }
            }

            // 2. Filtros Profundos (Ponto e Entidade dentro da Playlist)
            boolean precisaJoinPonto = filtro.getPontoId() != null
                    || (filtro.getNomePonto() != null && !filtro.getNomePonto().isBlank())
                    || (filtro.getPontoLetra() != null && !filtro.getPontoLetra().isBlank())
                    || filtro.getEntidadeId() != null
                    || filtro.getLinhaEntidade() != null;

            if (precisaJoinPonto) {
                Join<GiraEntity, GiraPlaylistEntity> joinGiraPlaylist = root.join("playlist", JoinType.INNER);
                Join<GiraPlaylistEntity, PlaylistEntity> joinPlaylist = joinGiraPlaylist.join("playlist", JoinType.INNER);
                Join<PlaylistEntity, PlaylistPontoEntity> joinPlaylistPonto = joinPlaylist.join("pontos", JoinType.INNER);
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

                // 3. Filtros Mais Profundos (Entidade do Ponto)
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