package com.api.batuque.config.security;

import com.api.batuque.adpater.output.database.usuarioJpa.entity.UsuarioEntity;
import com.api.batuque.domain.Exception.UnauthorizedException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret:chave_secreta_padrao_trocar_em_prod}")
    private String secret;

    public String gerarToken(UsuarioEntity username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("batuque-api")
                    .withSubject(username.getEmail())
                    .withClaim("nome", username.getNome())
                    .withClaim("role", username.getRole().name())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    public String gerarTokenRefresh(UsuarioEntity username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("batuque-api")
                    .withSubject(username.getEmail())
                    .withClaim("nome", username.getNome())
                    .withClaim("role", username.getRole().name())
                    .withExpiresAt(genExpirationDateMouth())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("batuque-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    public String coletarEmailJwt(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("batuque-api")
                    .build()
                    .verify(token)
                    .getSubject(); // Retorna o email do usuário
        } catch (TokenExpiredException exception) {
            throw new UnauthorizedException("Refresh token expirado. Faça login novamente.");
        } catch (JWTVerificationException exception) {
            throw new UnauthorizedException("Refresh token inválido.");
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    private Instant genExpirationDateMouth() {
        return LocalDateTime.now().plusMonths(1).toInstant(ZoneOffset.of("-03:00"));
    }


}