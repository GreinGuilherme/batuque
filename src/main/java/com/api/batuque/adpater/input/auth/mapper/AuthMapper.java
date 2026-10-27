package com.api.batuque.adpater.input.auth.mapper;

import com.api.batuque.adpater.input.auth.dto.AlterarSenhaRequest;
import com.api.batuque.adpater.input.auth.dto.EsqueciSenhaRequest;
import com.api.batuque.adpater.input.auth.dto.LoginRequest;
import com.api.batuque.adpater.input.auth.dto.LoginResponse;
import com.api.batuque.adpater.input.auth.dto.RedefinirSenhaRequest;
import com.api.batuque.adpater.input.auth.dto.RefreshTokenRequest;
import com.api.batuque.adpater.input.auth.dto.RegistroRequest;
import com.api.batuque.domain.model.RedefinirSenha;
import com.api.batuque.domain.model.RegistroUsuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    RegistroUsuario dtoToModelLogin(LoginRequest loginRequest);
    RegistroUsuario dtoToModelRegistro(RegistroRequest loginRequest);
    RegistroUsuario dtoToModelRegistro(EsqueciSenhaRequest loginRequest);
    RegistroUsuario dtoToModelRefresh(RefreshTokenRequest loginRequest);
    LoginResponse modelToTokenReponse(RegistroUsuario usuario);

    RedefinirSenha dtoToModelRedefinirSenha(RedefinirSenhaRequest request);
    RedefinirSenha dtoToModelRedefinirSenha(AlterarSenhaRequest request);
}
