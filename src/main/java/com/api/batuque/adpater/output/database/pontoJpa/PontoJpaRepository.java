package com.api.batuque.adpater.output.database.pontoJpa;

import com.api.batuque.adpater.output.database.pontoJpa.entity.ControlePontoEntity;
import com.api.batuque.domain.enums.LinhaEntidadeEnum;
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
        WHERE LOWER(p.nomePonto) LIKE LOWER(CONCAT('%', :nomePonto, '%'))
    """)
    List<ControlePontoEntity> buscarPorNomePonto(@Param("nomePonto") String nomePonto);

    // Query para busca parcial na letra:
    @Query("""
        SELECT p FROM ControlePontoEntity p 
        JOIN p.entidade e 
        WHERE LOWER(p.pontoLetra) LIKE LOWER(CONCAT('%', :pontoLetra, '%'))
    """)
    List<ControlePontoEntity> buscarPorPontoLetra(@Param("pontoLetra") String pontoLetra);

    @Query("""
        SELECT p FROM ControlePontoEntity p 
        JOIN p.entidade e 
        WHERE LOWER(e.nomeEntidade) LIKE LOWER(CONCAT('%', :nomeEntidade, '%'))
    """)
    List<ControlePontoEntity> buscarPorNomeEntidade(@Param("nomeEntidade") String nomeEntidade);

    @Query("""
        SELECT p FROM ControlePontoEntity p 
        JOIN p.entidade e 
        WHERE e.linhaEntidade = :linha
    """)
    List<ControlePontoEntity> buscarPorLinhaEntidade(@Param("linha") LinhaEntidadeEnum linha);
}
