package com.api.batuque.adpater.output.database.pontoJpa;

import com.api.batuque.adpater.output.database.entidadeJpa.entity.EntidadeEntity;
import com.api.batuque.adpater.output.database.pontoJpa.entity.ControlePontoEntity;
import com.api.batuque.domain.model.PontoEntidade;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PontoSpecifications {

    public static Specification<ControlePontoEntity> comFiltro(PontoEntidade filtro) {
        return (root, query, cb) -> {
            query.distinct(true);
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }

            if (filtro.getNomePonto() != null && !filtro.getNomePonto().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nomePonto")), "%" + filtro.getNomePonto().toLowerCase() + "%"));
            }

            if (filtro.getPontoLetra() != null && !filtro.getPontoLetra().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("pontoLetra")), "%" + filtro.getPontoLetra().toLowerCase() + "%"));
            }

            // Realiza o JOIN com 'entidade' para consultar campos da tabela relacionada
            boolean precisaJoinEntidade = filtro.getEntidadeId() != null
                    || (filtro.getNomeEntidade() != null && !filtro.getNomeEntidade().isBlank())
                    || filtro.getLinhaEntidade() != null;

            if (precisaJoinEntidade) {
                Join<ControlePontoEntity, EntidadeEntity> joinEntidade = root.join("entidade", JoinType.INNER);

                if (filtro.getEntidadeId() != null) {
                    predicates.add(cb.equal(joinEntidade.get("id"), filtro.getEntidadeId()));
                }

                if (filtro.getNomeEntidade() != null && !filtro.getNomeEntidade().isBlank()) {
                    predicates.add(cb.like(cb.lower(joinEntidade.get("nomeEntidade")), "%" + filtro.getNomeEntidade().toLowerCase() + "%"));
                }

                if (filtro.getLinhaEntidade() != null) {
                    predicates.add(cb.equal(joinEntidade.get("linhaEntidade"), filtro.getLinhaEntidade()));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}