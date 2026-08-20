package com.api.batuque.adpater.input.entidade;

import com.api.batuque.adpater.input.entidade.dto.EntidadeRequest;
import com.api.batuque.adpater.input.entidade.mapper.EntidadeRequestMapper;
import com.api.batuque.domain.port.input.ControleEntidadeInputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/entidade")
public class EntidadeController {

    private final ControleEntidadeInputPort entidadeInputPort;
    private final EntidadeRequestMapper entidadeRequestMapper;

    @PostMapping(value = "/cadastrar", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cadastrarEntidade (@RequestBody EntidadeRequest request) {
        log.info("[SALVAR ENTIDADE] - Iniciando processo para salvar o ponto");
        var response = entidadeRequestMapper.dtoToModel(request);
        entidadeInputPort.salvarEntidade(response);
        log.info("[SALVAR ENTIDADE] - Inclusão realizada com sucesso");
        return ResponseEntity.ok().build();
    }
}
