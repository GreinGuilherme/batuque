package com.api.batuque.adpater.output.database.pontoJpa;

import com.api.batuque.adpater.output.database.pontoJpa.entity.ControlePontoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PontoJpaRepository extends JpaRepository<ControlePontoEntity, Integer> {

    @Query("""
        SELECT p FROM ControlePontoEntity p 
        JOIN p.entidade e 
        WHERE LOWER(e.nomeEntidade) LIKE LOWER(CONCAT('%', :nomeEntidade, '%'))
    """)
    List<ControlePontoEntity> buscarPorNomeEntidade(@Param("nomeEntidade") String nomeEntidade);
}
