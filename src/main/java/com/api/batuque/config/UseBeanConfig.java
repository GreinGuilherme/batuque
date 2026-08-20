package com.api.batuque.config;

import com.api.batuque.domain.port.input.ControleEntidadeInputPort;
import com.api.batuque.domain.port.input.ControlePontoInputPort;
import com.api.batuque.domain.port.output.EntidadeRepositoryOutputPort;
import com.api.batuque.domain.port.output.PontoRepositoryOutputPort;
import com.api.batuque.domain.usecase.ControleEntidadeUseCase;
import com.api.batuque.domain.usecase.ControlePontoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseBeanConfig {

    @Bean
    public ControlePontoInputPort controlePontoInputPort(
            PontoRepositoryOutputPort pontoRepository,
            EntidadeRepositoryOutputPort entidadeRepositoryOutputPort
    ) {
        return new ControlePontoUseCase(pontoRepository, entidadeRepositoryOutputPort);
    }

    @Bean
    public ControleEntidadeInputPort controleEntidadeInputPort(
            EntidadeRepositoryOutputPort entidadeRepositoryOutputPort
    ) {
        return new ControleEntidadeUseCase(entidadeRepositoryOutputPort);
    }
}
