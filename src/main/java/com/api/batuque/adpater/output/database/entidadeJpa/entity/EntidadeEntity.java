package com.api.batuque.adpater.output.database.entidadeJpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
    @SequenceGenerator(
            name = "produto_seq",       // Nome do gerador
            sequenceName = "produto_seq", // Nome da sequência no banco
            allocationSize = 1          // Incremento
    )
    private Integer id;

    private String nomePonto;

    private String ponto;

    private String entidade;
}
