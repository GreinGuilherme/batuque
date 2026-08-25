package com.api.batuque.adpater.output.database.entidadeJpa;

import com.api.batuque.adpater.output.database.entidadeJpa.entity.EntidadeEntity;
import com.api.batuque.domain.enums.LinhaEntidadeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntidadeJpaRepository extends JpaRepository<EntidadeEntity, Integer> {
    Optional<EntidadeEntity> findByNomeEntidade(String nomeEntidade);

    @Query("""
        SELECT p FROM EntidadeEntity p
        WHERE LOWER(p.falange) LIKE LOWER(CONCAT('%', :falange, '%'))
    """)
    List<EntidadeEntity> findByFalange(@Param("falange") String falange);

    @Query("""
        SELECT p FROM EntidadeEntity p
        WHERE p.linhaEntidade = :linhaEntidade
    """)
    List<EntidadeEntity> findByLinha(@Param("linhaEntidade") LinhaEntidadeEnum linhaEntidade);
}
