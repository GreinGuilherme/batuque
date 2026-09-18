package com.api.batuque.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Login {

    private String email;
    private String senha;
}
