package com.api.batuque.domain.enums;

import lombok.Getter;

@Getter
public enum UsuarioRoleEnum {
    ADM("ADM"),
    FILHO("FILHO"),
    USUARIO("USUARIO");

    private String role;

    UsuarioRoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}