package com.api.batuque.domain.port.output;

import com.api.batuque.domain.model.RegistroUsuario;

public interface AuthRepositoryOutputPort {
    RegistroUsuario findByEmail(RegistroUsuario usuario);
    void save(RegistroUsuario usuario);
    void save(RegistroUsuario usuario, String senhaCripografada);
}
