package com.api.batuque.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Gira {

    private Long id;
    private String nomeGira;
    private String descricao;
    private LocalDate dataGira;
    private List<ItemGira> playlist;
}