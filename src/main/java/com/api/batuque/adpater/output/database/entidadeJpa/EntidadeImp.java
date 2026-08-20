package com.api.batuque.adpater.output.database.entidadeJpa;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EntidadeImp implements EntidadeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public Integer incluirEntidade(String nomeEntidade, String entidade) {
        Integer idGerado = jdbcTemplate.queryForObject("""
                SELECT ISNULL(MAX(ID),0) + 1 
                FROM ENTIDADE WITH (UPLOCK< HOLDLOCK)
                """, Integer.class);

        jdbcTemplate.update("""
                INSERT INTO ENTIDADE (ID, NOMEPONTO, ENTIDADE)
                VALUES (?,?,?)
                """, idGerado, nomeEntidade, entidade);
        return idGerado;
    }
}
