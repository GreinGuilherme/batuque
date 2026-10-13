package com.api.batuque.adpater.input.gira.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class GiraRequest {

    @NotBlank(message = "O nome da gira é obrigatório")
    @Size(max = 100, message = "Máximo de 100 caracteres")
    private String nomeGira;

    @Size(max = 200, message = "Máximo de 100 caracteres")
    private String descricao;

    private LocalDate dataGira = LocalDate.now();

    @NotEmpty(message = "A playlist deve conter ao menos um ponto")
    @Valid
    private List<ItemGiraRequest> playlist;
}