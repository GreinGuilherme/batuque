package com.api.batuque.adpater.input.ponto;

import com.api.batuque.adpater.input.ponto.dto.PontoRequest;
import com.api.batuque.adpater.input.ponto.mapper.PontoRequestMapper;
import com.api.batuque.domain.port.input.ControlePontoInputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/gestaopontos")
public class GestaoPontosController {

    private final ControlePontoInputPort controlePonto;
    private final PontoRequestMapper pontoRequestMapper;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarPonto (@RequestBody PontoRequest request) {
        log.info("[SALVAR PONTO] - Iniciando processo para salvar o ponto");
        var response = pontoRequestMapper.dtoToModel(request);
        controlePonto.entradaPonto(response);
        log.info("[SALVAR PONTO] - Inclusão realizada com sucesso");
        return ResponseEntity.ok().build();
    }
}
