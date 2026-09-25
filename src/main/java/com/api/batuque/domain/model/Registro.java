package com.api.batuque.domain.model;

import com.api.batuque.domain.enums.UsuarioRoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Registro {

    private String nome;
    private String email;
    private String senha;
    private UsuarioRoleEnum role;
}
