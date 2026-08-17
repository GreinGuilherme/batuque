package com.api.batuque.adpater.input;

import com.api.batuque.adpater.input.dto.PontoRequest;
import com.api.batuque.adpater.input.mapper.PontoRequestMapper;
import com.api.batuque.domain.port.input.ControlePontoInputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/gestaopontos")
public class GestaoPontosController {

    private final ControlePontoInputPort controlePonto;
    private final PontoRequestMapper pontoRequestMapper;
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarPonto (PontoRequest request) {
        log.info("[SALVAR PONTO] - Iniciando processo para salvar o ponto");
        var response = pontoRequestMapper.dtoToModel(request);
        controlePonto.entradaPonto(response);
        log.info("[SALVAR PONTO] - Inclusão realizada com sucesso");
        return ResponseEntity.ok().build();
    }
}
