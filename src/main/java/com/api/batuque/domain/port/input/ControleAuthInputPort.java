package com.api.batuque.domain.port.input;

import com.api.batuque.domain.model.RedefinirSenha;
import com.api.batuque.domain.model.RegistroUsuario;

public interface ControleAuthInputPort {
    RegistroUsuario login(RegistroUsuario login);
    void registrar(RegistroUsuario registro);
    RegistroUsuario refresh(RegistroUsuario login);
    void esqueciSenha(RegistroUsuario login);
    void redefinirSenha(RedefinirSenha login);
    void alterarSenha(RedefinirSenha login);
}
