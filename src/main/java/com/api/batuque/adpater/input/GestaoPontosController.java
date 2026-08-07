package com.api.batuque.adpater.input;

import com.api.batuque.domain.port.input.ControlePonto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
@RequestMapping("/gestaoPontos")
public class GestaoPontosController {

    private final ControlePonto controlePonto;

    public GestaoPontosController(com.api.batuque.domain.port.input.ControlePonto controlePonto) {
        this.controlePonto = controlePonto;
    }

    @PostMapping("/entrada")
    public ResponseEntity<?> cadastrarPonto (String ponto) {
        return ResponseEntity.ok().build();
    }
}
