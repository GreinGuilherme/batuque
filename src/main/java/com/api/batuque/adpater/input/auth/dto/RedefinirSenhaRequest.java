package com.api.batuque.adpater.input.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RedefinirSenhaRequest(
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "O código de verificação é obrigatório")
        String codigo,

        @NotBlank(message = "A nova senha é obrigatória")
        @Size(min = 6, max = 12, message = "Minimo de 6 e Máximo de 12 caracteres.")
        String novaSenha
) {
}
