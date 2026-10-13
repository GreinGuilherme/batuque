package com.api.batuque.adpater.output.database.giraJpa;

import com.api.batuque.adpater.output.database.giraJpa.entity.GiraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GiraJpaRepository extends JpaRepository<GiraEntity, Long>, JpaSpecificationExecutor<GiraEntity> {

    @Query("""
        SELECT DISTINCT g FROM GiraEntity g
        LEFT JOIN FETCH g.playlist gp
        LEFT JOIN FETCH gp.playlist p
        WHERE g.id = :id
    """)
    Optional<GiraEntity> findByIdWithPlaylist(@Param("id") Long id);

    @Query("""
        SELECT DISTINCT g FROM GiraEntity g
        LEFT JOIN FETCH g.playlist gp
        LEFT JOIN FETCH gp.playlist p
    """)
    List<GiraEntity> findAllWithPlaylist();
}