package com.api.batuque.adpater.output.database.playlistJpa;

import com.api.batuque.adpater.output.database.playlistJpa.entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistJpaRepository extends JpaRepository<PlaylistEntity, Long> {

    // Query especializada para saber se um ponto específico já está na playlist
    @Query("""
        SELECT CASE WHEN COUNT(pp) > 0 THEN true ELSE false END 
        FROM PlaylistPontoEntity pp 
        WHERE pp.playlist.id = :playlistId AND pp.ponto.id = :pontoId
    """)
    boolean existsPontoInPlaylist(@Param("playlistId") Long playlistId,
                                  @Param("pontoId") Long pontoId);

    @Query("""
        SELECT DISTINCT p FROM PlaylistEntity p
        LEFT JOIN FETCH p.pontos pp
        LEFT JOIN FETCH pp.ponto pt
        LEFT JOIN FETCH pt.entidade
        WHERE p.id = :id
    """)
    Optional<PlaylistEntity> findByIdWithPontos(@Param("id") Long id);

    @Query("""
        SELECT DISTINCT p FROM PlaylistEntity p
        LEFT JOIN FETCH p.pontos pp
        LEFT JOIN FETCH pp.ponto pt
        LEFT JOIN FETCH pt.entidade
    """)
    List<PlaylistEntity> findAllWithPontos();
}
