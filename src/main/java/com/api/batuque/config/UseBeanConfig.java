package com.api.batuque.config;

import com.api.batuque.adpater.output.database.pontoJpa.PontoRepository;
import com.api.batuque.domain.port.input.ControlePontoInputPort;
import com.api.batuque.domain.usecase.ControlePontoUseCase;
import org.springframework.context.annotation.Bean;

public class UseBeanConfig {

    @Bean
    public ControlePontoInputPort controlePontoInputPort(
            PontoRepository pontoRepository
    ) {
        return new ControlePontoUseCase(pontoRepository);
    }
}
