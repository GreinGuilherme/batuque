package com.api.batuque.adpater.output.database.entidadeJpa.entity;

import com.api.batuque.domain.enums.LinhaEntidadeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "entidade")
@NoArgsConstructor
@AllArgsConstructor
public class EntidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nomeEntidade;

    @Column(name = "falange", length = 50)
    private String falange;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false, length = 50)
    private LinhaEntidadeEnum linhaEntidade;
}
