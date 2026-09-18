package com.api.batuque.adpater.input.auth.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

    @Size(max = 50, message = "Máximo de 50 caracteres.")
    @NotNull(message = "Não pode ser nullo.")
    private String email;

    @NotNull(message = "Não pode ser nullo.")
    @Size(min = 6, max = 12, message = "Minimo de 6 e Máximo de 12 caracteres.")
    private String senha;
}
