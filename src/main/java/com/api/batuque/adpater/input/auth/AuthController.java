package com.api.batuque.adpater.input.auth;

import com.api.batuque.adpater.input.auth.dto.LoginRequest;
import com.api.batuque.adpater.input.auth.dto.RegistroRequest;
import com.api.batuque.adpater.output.database.usuarioJpa.UsuarioJpaRepository;
import com.api.batuque.adpater.output.database.usuarioJpa.entity.UsuarioEntity;
import com.api.batuque.config.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioJpaRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getSenha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken(auth.getPrincipal().toString());

        return ResponseEntity.ok(token); // Retorna o JWT gerado
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody @Valid RegistroRequest data) {
        if (this.repository.findByEmail(data.getEmail()) != null) {
            return ResponseEntity.badRequest().body("E-mail já cadastrado");
        }

        String senhaCriptografada = new BCryptPasswordEncoder().encode(data.getSenha());
        UsuarioEntity novoUsuario = new UsuarioEntity(null, data.getNome(), data.getEmail(), senhaCriptografada, data.getRole());

        this.repository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }
}