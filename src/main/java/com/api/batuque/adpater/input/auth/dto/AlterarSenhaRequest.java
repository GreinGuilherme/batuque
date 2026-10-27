package com.api.batuque.adpater.input.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AlterarSenhaRequest(
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotNull(message = "Não pode ser nullo.")
        @Size(max = 12, message = "Máximo de 12 caracteres.")
        String senha,

        @NotBlank(message = "A nova senha é obrigatória")
        @Size(min = 6, max = 12, message = "Minimo de 6 e Máximo de 12 caracteres.")
        String novaSenha
) {
}
