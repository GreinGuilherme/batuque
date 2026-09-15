package com.api.batuque.adpater.input.auth.dto;

import com.api.batuque.domain.enums.UsuarioRoleEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegistroRequest {

    @Size(max = 50, message = "Máximo de 50 caracteres.")
    @NotNull(message = "Não pode ser nullo.")
    private String nome;

    @Size(max = 50, message = "Máximo de 50 caracteres.")
    @NotNull(message = "Não pode ser nullo.")
    private String email;

    @NotNull(message = "Não pode ser nullo.")
    @Size(max = 12, message = "Máximo de 12 caracteres.")
    private String senha;

    @NotNull(message = "Não pode ser nullo.")
    @Size(max = 12, message = "Máximo de 12 caracteres.")
    private UsuarioRoleEnum role;
}
