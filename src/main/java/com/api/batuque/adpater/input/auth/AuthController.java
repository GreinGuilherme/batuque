package com.api.batuque.adpater.input.auth;

import com.api.batuque.adpater.input.auth.dto.LoginRequest;
import com.api.batuque.adpater.input.auth.dto.LoginResponse;
import com.api.batuque.adpater.input.auth.dto.RefreshTokenRequest;
import com.api.batuque.adpater.input.auth.dto.RegistroRequest;
import com.api.batuque.adpater.output.database.usuarioJpa.UsuarioJpaRepository;
import com.api.batuque.adpater.output.database.usuarioJpa.entity.UsuarioEntity;
import com.api.batuque.config.security.TokenService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioJpaRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest login) {
        log.info("[LOGIN] - Iniciando processo verificar login");

        var usernamePassword = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        log.info("[CADASTRO LOGIN] - Gerando token para o usuário");
        UsuarioEntity user = (UsuarioEntity) auth.getPrincipal();
        if (user == null) {
           throw new EntityNotFoundException("Usuário não existe");
        }
        String tokenCurto = tokenService.gerarToken(user);
        String tokenRefresh = tokenService.gerarTokenRefresh(user);

        LoginResponse token = new LoginResponse();
        token.setToken(tokenCurto);
        token.setRefreshToken(tokenRefresh);

        log.info("[CADASTRO LOGIN] - Login do usuário efetuado com sucesso");
        return ResponseEntity.ok(token);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody @Valid RegistroRequest registro) {
        log.info("[CADASTRO LOGIN] - Iniciando processo cadastrar usuário");
        if (this.repository.findByEmail(registro.getEmail()) != null) {
            return ResponseEntity.badRequest().body("E-mail já cadastrado");
        }

        log.info("[CADASTRO LOGIN] - Processando senha do usuário");
        String senhaCriptografada = passwordEncoder.encode(registro.getSenha());
        UsuarioEntity novoUsuario = new UsuarioEntity(null, registro.getNome(), registro.getEmail(), senhaCriptografada, registro.getRole());

        this.repository.save(novoUsuario);
        log.info("[CADASTRO LOGIN] - Usuário cadastrado com sucesso");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest refreshToken) {
        log.info("[REFRESH] - Iniciando processo verificar login");
        String username = tokenService.coletarEmailJwt(refreshToken.getRefreshToken());
        UsuarioEntity user = (UsuarioEntity) repository.findByEmail(username);

        if (user == null) {
            return ResponseEntity.badRequest().body("[REFRESH] - Usuário não encontrado");
        }

        log.info("[REFRESH] - Gerando token para o usuário");
        String tokenCurto = tokenService.gerarToken(user);
        String tokenRefresh = tokenService.gerarTokenRefresh(user);

        LoginResponse token = new LoginResponse();
        token.setToken(tokenCurto);
        token.setRefreshToken(tokenRefresh);

        log.info("[REFRESH] - Token gerado com sucesso");
        return ResponseEntity.ok(token);
    }
}