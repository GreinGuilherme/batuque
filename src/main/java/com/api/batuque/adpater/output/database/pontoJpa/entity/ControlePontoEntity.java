package com.api.batuque.adpater.output.database.pontoJpa.entity;

import com.api.batuque.adpater.output.database.entidadeJpa.entity.EntidadeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private Long id;

    @Column(name = "titulo")
    private String nomePonto;

    @Column(name = "letra")
    private String pontoLetra;

    @Column(name = "audio_url")
    private String audioUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entidade_id")
    private EntidadeEntity entidade;
}
