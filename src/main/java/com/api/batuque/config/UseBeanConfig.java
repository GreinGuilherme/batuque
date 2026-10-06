package com.api.batuque.config;

import com.api.batuque.domain.port.input.ControleEntidadeInputPort;
import com.api.batuque.domain.port.input.ControlePlaylistInputPort;
import com.api.batuque.domain.port.input.ControlePontoInputPort;
import com.api.batuque.domain.port.output.EntidadeRepositoryOutputPort;
import com.api.batuque.domain.port.output.PlaylistRepositoryOutputPort;
import com.api.batuque.domain.port.output.PontoRepositoryOutputPort;
import com.api.batuque.domain.usecase.ControleEntidadeUseCase;
import com.api.batuque.domain.usecase.ControlePlaylistUseCase;
import com.api.batuque.domain.usecase.ControlePontoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

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
}
