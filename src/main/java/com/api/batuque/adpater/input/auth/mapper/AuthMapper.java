package com.api.batuque.adpater.input.auth.mapper;

import com.api.batuque.adpater.input.auth.dto.LoginRequest;
import com.api.batuque.adpater.input.auth.dto.RegistroRequest;
import com.api.batuque.domain.model.Login;
import com.api.batuque.domain.model.Registro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    Login dtoToModelLogin(LoginRequest loginRequest);
    Registro dtoToModelRegistro(RegistroRequest registroRequest);
}
