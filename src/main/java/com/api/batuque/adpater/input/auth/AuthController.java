package com.api.batuque.adpater.input.auth;

import com.api.batuque.adpater.input.auth.dto.AlterarSenhaRequest;
import com.api.batuque.adpater.input.auth.dto.EsqueciSenhaRequest;
import com.api.batuque.adpater.input.auth.dto.LoginRequest;
import com.api.batuque.adpater.input.auth.dto.LoginResponse;
import com.api.batuque.adpater.input.auth.dto.RedefinirSenhaRequest;
import com.api.batuque.adpater.input.auth.dto.RefreshTokenRequest;
import com.api.batuque.adpater.input.auth.dto.RegistroRequest;
import com.api.batuque.adpater.input.auth.mapper.AuthMapper;
import com.api.batuque.domain.model.RedefinirSenha;
import com.api.batuque.domain.model.RegistroUsuario;
import com.api.batuque.domain.port.input.ControleAuthInputPort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private ControleAuthInputPort controleAuthInputPort;
    private AuthMapper authMapper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest login) {
        log.info("[LOGIN] - Recebendo processo de login");
        RegistroUsuario usuario = authMapper.dtoToModelLogin(login);
        RegistroUsuario reponse = controleAuthInputPort.login(usuario);
        LoginResponse token = authMapper.modelToTokenReponse(reponse);
        log.info("[LOGIN] - Login do usuário efetuado com sucesso");
        return ResponseEntity.ok(token);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody @Valid RegistroRequest registro) {
        log.info("[CADASTRO LOGIN] - Recebendo processo cadastrar usuário");
        RegistroUsuario usuario = authMapper.dtoToModelRegistro(registro);
        controleAuthInputPort.registrar(usuario);
        log.info("[CADASTRO LOGIN] - Usuário cadastrado com sucesso!");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest refreshToken) {
        log.info("[REFRESH] - Recebendo processo verificar login");
        RegistroUsuario usuario = authMapper.dtoToModelRefresh(refreshToken);
        RegistroUsuario reponse = controleAuthInputPort.refresh(usuario);
        LoginResponse token = authMapper.modelToTokenReponse(reponse);
        log.info("[REFRESH] - Token gerado com sucesso!");
        return ResponseEntity.ok(token);
    }

    @PostMapping("/esqueci-senha")
    public ResponseEntity<Map<String, String>> esqueciSenha(@RequestBody @Valid EsqueciSenhaRequest request) {
        log.info("[ESQUECI SENHA] - Recebendo processo recuperaçao de senha.");
        RegistroUsuario usuario = authMapper.dtoToModelRegistro(request);
        controleAuthInputPort.esqueciSenha(usuario);
        log.info("[ESQUECI SENHA] - Processo recuperaçao senha concluido com sucesso!");
        return ResponseEntity.ok(Map.of("mensagem", "Caso o e-mail esteja cadastrado, você receberá um código de recuperação em instantes."));
    }

    @PostMapping("/redefinir-senha")
    public ResponseEntity<Map<String, String>> redefinirSenha(@RequestBody @Valid RedefinirSenhaRequest request) {
        log.info("[REDEFINIR SENHA] - Recebendo processo redefinir senha.");
        RedefinirSenha usuario = authMapper.dtoToModelRedefinirSenha(request);
        controleAuthInputPort.redefinirSenha(usuario);
        log.info("[REDEFINIR SENHA] - Processo redefinir senha concluido com sucesso!");
        return ResponseEntity.ok(Map.of("mensagem", "Senha atualizada com sucesso! Faça login com sua nova senha."));
    }

    @PostMapping("/alterar-senha")
    public ResponseEntity<Map<String, String>> alterarSenha(@RequestBody @Valid AlterarSenhaRequest request) {
        log.info("[ALTERAR SENHA] - Recebendo processo alterar senha.");
        RedefinirSenha usuario = authMapper.dtoToModelRedefinirSenha(request);
        controleAuthInputPort.alterarSenha(usuario);
        log.info("[ALTERAR SENHA] - Processo alterar senha concluido com sucesso!");
        return ResponseEntity.ok(Map.of("mensagem", "Senha atualizada com sucesso! Faça login com sua nova senha."));
    }
}