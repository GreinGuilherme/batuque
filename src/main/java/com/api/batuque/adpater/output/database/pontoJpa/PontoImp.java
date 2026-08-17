package com.api.batuque.adpater.output.database.pontoJpa;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PontoImp implements PontoRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Integer incluirPonto(String nomePonto, String ponto, String entidade) {
        Integer idGerado = jdbcTemplate.queryForObject("""
                SELECT ISNULL(MAX(ID),0) + 1 
                FROM PONTO WITH (UPLOCK< HOLDLOCK)
                """, Integer.class);

        jdbcTemplate.update("""
                INSERT INTO PONTO (ID, NOMEPONTO, PONTO, ENTIDADE)
                VALUES (?,?,?,?)
                """, idGerado, nomePonto, ponto, entidade);
        return idGerado;
    }
}
