package com.api.batuque.domain.usecase;

import com.api.batuque.adpater.output.database.usuarioJpa.entity.UsuarioEntity;
import com.api.batuque.config.security.RecuperacaoSenhaService;
import com.api.batuque.config.security.TokenService;
import com.api.batuque.domain.Exception.BadRequestException;
import com.api.batuque.domain.Exception.NotFoundException;
import com.api.batuque.domain.model.RedefinirSenha;
import com.api.batuque.domain.model.RegistroUsuario;
import com.api.batuque.domain.port.input.ControleAuthInputPort;
import com.api.batuque.domain.port.output.AuthRepositoryOutputPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@AllArgsConstructor
public class ControleAuthUseCase implements ControleAuthInputPort {

    //@Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private RecuperacaoSenhaService recuperacaoSenhaService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    private final AuthRepositoryOutputPort repositoryOutputPort;


    public RegistroUsuario login(RegistroUsuario usuario) {
        log.info("[LOGIN] - Iniciando processo verificar login...");

        var usernamePassword = new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        log.info("[CADASTRO LOGIN] - Gerando token para o usuário");
        UsuarioEntity user = (UsuarioEntity) auth.getPrincipal();
        if (user == null) {
            throw new EntityNotFoundException("Usuário não existe");
        }
        usuario.setRole(user.getRole());
        String tokenCurto = tokenService.gerarToken(usuario);
        String tokenRefresh = tokenService.gerarTokenRefresh(usuario);

        usuario.setToken(tokenCurto);
        usuario.setRefreshToken(tokenRefresh);

        log.info("[CADASTRO LOGIN] - Finalizando processo verificar login.");
        return usuario;
    }

    public void registrar(RegistroUsuario usuario) {
        log.info("[CADASTRO LOGIN] - Iniciando processo cadastrar usuário...");

        if (repositoryOutputPort.findByEmail(usuario) != null) {
            throw new BadRequestException("E-mail já cadastrado");
        }

        log.info("[CADASTRO LOGIN] - Processando senha do usuário");
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());

        repositoryOutputPort.save(usuario, senhaCriptografada);
        log.info("[CADASTRO LOGIN] - Finalizado processo cadastrar usuário!");
    }

    public RegistroUsuario refresh(RegistroUsuario usuario) {
        log.info("[REFRESH] - Iniciando processo verificar login...");
        String username = tokenService.coletarEmailJwt(usuario.getRefreshToken());
        usuario.setEmail(username);
        RegistroUsuario user = repositoryOutputPort.findByEmail(usuario);

        if (user == null) {
            throw new NotFoundException("[REFRESH] - Usuário não encontrado");
        }

        log.info("[REFRESH] - Gerando token para o usuário");
        String tokenCurto = tokenService.gerarToken(user);
        String tokenRefresh = tokenService.gerarTokenRefresh(user);

        usuario.setToken(tokenCurto);
        usuario.setRefreshToken(tokenRefresh);

        log.info("[REFRESH] - Finalizando processo verificar login.");
        return usuario;
    }

    public void esqueciSenha(RegistroUsuario usuario) {
        try{
            log.info("[ESQUECI SENHA] - Iniciando processo recuperaçao de senha...");
            recuperacaoSenhaService.solicitarRecuperacao(usuario.getEmail());
            log.info("[ESQUECI SENHA] - Finalizando processo recuperaçao de senha.");
        } catch (RuntimeException e) {
            throw new BadRequestException("Erro ao solicitar recuperação de senha!");
        }
    }

    @PostMapping("/redefinir-senha")
    public void redefinirSenha(RedefinirSenha senha) {
        try{
            log.info("[REDEFINIR SENHA] - Iniciando processo redefinir senha...");
            recuperacaoSenhaService.redefinirSenha(senha.getEmail(), senha.getCodigo(), senha.getNovaSenha());
            log.info("[REDEFINIR SENHA] - Finalizando processo redefinir senha.");
        } catch (RuntimeException e) {
            throw new BadRequestException("Erro ao solicitar recuperação de senha!");
        }
    }

    @PostMapping("/alterar-senha")
    public void alterarSenha(RedefinirSenha senha) {
        log.info("[REDEFINIR SENHA] - Iniciando processo redefinir senha.");
        recuperacaoSenhaService.alterarSenha(senha);
        log.info("[REDEFINIR SENHA] - Finalziando processo redefinir senha.");
    }
}
