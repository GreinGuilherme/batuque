package com.api.batuque.adpater.output.database.pontoJpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "ponto")
@NoArgsConstructor
@AllArgsConstructor
public class ControlePontoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "titulo")
    private String nomePonto;

    @Column(name = "letra")
    private String ponto;

    @Column(name = "audio_url")
    private String audioUrl;

    @Column(name = "entidade_id")
    private Integer entidade;
}
