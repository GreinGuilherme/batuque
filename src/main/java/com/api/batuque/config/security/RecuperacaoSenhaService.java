package com.api.batuque.config.security;

import com.api.batuque.adpater.output.database.usuarioJpa.UsuarioJpaRepository;
import com.api.batuque.adpater.output.database.usuarioJpa.entity.UsuarioEntity;
import com.api.batuque.domain.Exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class RecuperacaoSenhaService {

    private final UsuarioJpaRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final SecureRandom secureRandom = new SecureRandom();

    @Value("${spring.mail.username}")
    private String remetente;

    public RecuperacaoSenhaService(
            UsuarioJpaRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JavaMailSender mailSender) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

    @Transactional
    public void solicitarRecuperacao(String email) {
        log.info("[ESQUECI SENHA] - Procurando usuario");
        Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findUsuarioByEmail(email);

        // Se o e-mail nao existir, encerra silenciosamente por seguranca (Anti-Enumeracao)
        if (usuarioOpt.isEmpty()) {
            log.info("[ESQUECI SENHA] - Usuario nao encontrado.");
            return;
        }

        log.info("[ESQUECI SENHA] - Usuario encontrado.");
        UsuarioEntity usuario = usuarioOpt.get();

        log.info("[ESQUECI SENHA] - Gerando codigo aleatorio.");
        // Gera codigo numerico aleatorio de 6 digitos (ex: 048291)
        String codigo = String.format("%06d", secureRandom.nextInt(1_000_000));

        // Salva o hash do codigo e validade de 15 minutos
        usuario.setTokenRecuperacao(passwordEncoder.encode(codigo));
        usuario.setTokenExpiracao(LocalDateTime.now().plusMinutes(15));
        usuarioRepository.save(usuario);

        log.info("[ESQUECI SENHA] - Enviando email ao usuario.");
        // Dispara o e-mail de forma assincrona
        enviarEmailCodigo(usuario.getEmail(), usuario.getNome(), codigo);
    }

    @Async
    protected void enviarEmailCodigo(String emailDestino, String nomeUsuario, String codigo) {
        CompletableFuture.runAsync(() -> {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(remetente);
                message.setTo(emailDestino);
                message.setSubject("Batuque - Código de Recuperação de Senha");
                message.setText(String.format(
                        "Olá, %s!\n\n" +
                                "Você solicitou a recuperação de senha no aplicativo Batuque.\n\n" +
                                "Seu código de verificação é: %s\n\n" +
                                "Insira este código no aplicativo para cadastrar sua nova senha. " +
                                "Este código expira em 15 minutos.\n\n" +
                                "Saravá!\nEquipe Batuque",
                        nomeUsuario, codigo
                ));
                mailSender.send(message);
            } catch (Exception e) {
                // Loga o erro no console sem derrubar a requisicao HTTP do usuario
                log.error("[ERRO SMTP] Falha ao enviar e-mail de recuperacao: " + e.getMessage());
            }
        });
    }

    @Transactional
    public void redefinirSenha(String email, String codigoInformado, String novaSenha) {
        log.info("[REDEFINIR SENHA] - Iniciando procurando login do usuario.");
        UsuarioEntity usuario;
        try{
            usuario = (UsuarioEntity) usuarioRepository.findByEmail(email);
        } catch (RuntimeException e) {
            throw new UnauthorizedException("Código inválido ou expirado.");
        }

        log.info("[REDEFINIR SENHA] - Verificando token usuario.");
        // Verifica se ha um token ativo e se nao expirou
        if (usuario.getTokenRecuperacao() == null ||
                usuario.getTokenExpiracao() == null ||
                LocalDateTime.now().isAfter(usuario.getTokenExpiracao())) {
            throw new UnauthorizedException("O código de recuperação expirou. Solicite um novo.");
        }

        // Compara o codigo digitado com o hash salvo no banco
        if (!passwordEncoder.matches(codigoInformado, usuario.getTokenRecuperacao())) {
            throw new UnauthorizedException("Código de verificação incorreto.");
        }

        log.info("[REDEFINIR SENHA] - Iniciando processo atualizaçao de senha");
        // Atualiza para a nova senha definitiva e limpa o token de uso unico
        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuario.setTokenRecuperacao(null);
        usuario.setTokenExpiracao(null);

        log.info("[REDEFINIR SENHA] - Salvando nova senha.");
        usuarioRepository.save(usuario);
    }
}