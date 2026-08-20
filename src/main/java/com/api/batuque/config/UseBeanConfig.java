package com.api.batuque.config;

import com.api.batuque.adpater.output.database.entidadeJpa.EntidadeRepository;
import com.api.batuque.adpater.output.database.pontoJpa.PontoRepository;
import com.api.batuque.domain.port.input.ControleEntidadeInputPort;
import com.api.batuque.domain.port.input.ControlePontoInputPort;
import com.api.batuque.domain.usecase.ControleEntidadeUseCase;
import com.api.batuque.domain.usecase.ControlePontoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseBeanConfig {

    @Bean
    public ControlePontoInputPort controlePontoInputPort(
            PontoRepository pontoRepository
    ) {
        return new ControlePontoUseCase(pontoRepository);
    }

    @Bean
    public ControleEntidadeInputPort controleEntidadeInputPort(
            EntidadeRepository entidadeRepository
    ) {
        return new ControleEntidadeUseCase(entidadeRepository);
    }
}
