package com.api.batuque.domain.model;

import com.api.batuque.domain.enums.UsuarioRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroUsuario {

    private String nome;
    private String email;
    private String senha;
    private UsuarioRoleEnum role;
    private String token;
    private String refreshToken;
    private String tokenRecuperacao;
    private LocalDateTime tokenExpiracao;
}
