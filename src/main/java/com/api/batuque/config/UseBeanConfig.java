package com.api.batuque.config;

import com.api.batuque.config.security.RecuperacaoSenhaService;
import com.api.batuque.config.security.TokenService;
import com.api.batuque.domain.port.input.ControleAuthInputPort;
import com.api.batuque.domain.port.input.ControleEntidadeInputPort;
import com.api.batuque.domain.port.input.ControleGiraInputPort;
import com.api.batuque.domain.port.input.ControlePlaylistInputPort;
import com.api.batuque.domain.port.input.ControlePontoInputPort;
import com.api.batuque.domain.port.output.AuthRepositoryOutputPort;
import com.api.batuque.domain.port.output.EntidadeRepositoryOutputPort;
import com.api.batuque.domain.port.output.GiraRepositoryOutputPort;
import com.api.batuque.domain.port.output.PlaylistRepositoryOutputPort;
import com.api.batuque.domain.port.output.PontoRepositoryOutputPort;
import com.api.batuque.domain.usecase.ControleAuthUseCase;
import com.api.batuque.domain.usecase.ControleEntidadeUseCase;
import com.api.batuque.domain.usecase.ControleGiraUseCase;
import com.api.batuque.domain.usecase.ControlePlaylistUseCase;
import com.api.batuque.domain.usecase.ControlePontoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UseBeanConfig {

    @Bean
    public ControlePontoInputPort controlePontoInputPort(
            PontoRepositoryOutputPort pontoRepository,
            EntidadeRepositoryOutputPort entidadeRepositoryOutputPort,
            SimpMessagingTemplate messagingTemplate
    ) {
        return new ControlePontoUseCase(pontoRepository, entidadeRepositoryOutputPort, messagingTemplate);
    }

    @Bean
    public ControleEntidadeInputPort controleEntidadeInputPort(
            EntidadeRepositoryOutputPort entidadeRepositoryOutputPort,
            SimpMessagingTemplate messagingTemplate
    ) {
        return new ControleEntidadeUseCase(entidadeRepositoryOutputPort, messagingTemplate);
    }

    @Bean
    public ControlePlaylistInputPort controlePlaylistInputPort(
            PlaylistRepositoryOutputPort playlistRepositoryOutputPort,
            SimpMessagingTemplate messagingTemplate
    ) {
        return new ControlePlaylistUseCase(playlistRepositoryOutputPort, messagingTemplate);
    }

    @Bean
    public ControleGiraInputPort controleGiraInputPort(
            GiraRepositoryOutputPort giraRepositoryOutputPort,
            SimpMessagingTemplate messagingTemplate
    ) {
        return new ControleGiraUseCase(giraRepositoryOutputPort, messagingTemplate);
    }

    @Bean
    public ControleAuthInputPort controleAuthInputPort(
            AuthenticationManager authenticationManager,
            RecuperacaoSenhaService recuperacaoSenhaService,
            PasswordEncoder passwordEncoder,
            TokenService tokenService,
            AuthRepositoryOutputPort repositoryOutputPort
    ) {
        return new ControleAuthUseCase(authenticationManager, recuperacaoSenhaService, passwordEncoder, tokenService, repositoryOutputPort);
    }
}
