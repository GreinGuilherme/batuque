package com.api.batuque.adpater.output.database.entidadeJpa;

import com.api.batuque.adpater.output.database.entidadeJpa.entity.EntidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntidadeJpaRepository extends JpaRepository<EntidadeEntity, Integer> {
    Optional<EntidadeEntity> findByNomeEntidade(String nomeEntidade);
}
