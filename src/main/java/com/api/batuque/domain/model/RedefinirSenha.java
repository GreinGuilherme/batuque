package com.api.batuque.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RedefinirSenha {

    private String email;
    private String senha;
    private String token;
    private String codigo;
    private String novaSenha;
    private String refreshToken;
}
