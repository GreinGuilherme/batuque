package com.api.batuque.adpater.input.entidade;

import com.api.batuque.adpater.input.entidade.dto.EntidadeFiltroRequest;
import com.api.batuque.adpater.input.entidade.dto.EntidadeRequest;
import com.api.batuque.adpater.input.entidade.dto.EntidadeResponse;
import com.api.batuque.adpater.input.entidade.mapper.EntidadeRequestMapper;
import com.api.batuque.domain.model.Entidades;
import com.api.batuque.domain.port.input.ControleEntidadeInputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping(value = "/buscar")
    public ResponseEntity<List<EntidadeResponse>> cadastrarEntidade () {
        log.info("[BUSCAR ENTIDADE] - Iniciando processo para buscar todas as entidades.");
        List<Entidades> response = entidadeInputPort.buscarEntidades();
        log.info("[BUSCAR ENTIDADE] - Busca de todas as entidades realizada com sucesso");
        List<EntidadeResponse> responselist = entidadeRequestMapper.modelListToDtolist(response);
        return ResponseEntity.ok().body(responselist);
    }

    @GetMapping(value = "/buscar/filtrar")
    public ResponseEntity<List<EntidadeResponse>> filtrarEntidade (@ModelAttribute EntidadeFiltroRequest request) {
        log.info("[FILTRAR ENTIDADE] - Iniciando processo para buscar a entidade.");
        Entidades domain = entidadeRequestMapper.dtoFiltroToModel(request);
        List<Entidades> response = entidadeInputPort.filtrarEntidades(domain);
        log.info("[FILTRAR ENTIDADE] - Busca da entidade realizada com sucesso");
        List<EntidadeResponse> responselist = entidadeRequestMapper.modelToDtoFiltroList(response);
        return ResponseEntity.ok().body(responselist);
    }

    @DeleteMapping(value = "/deletar/{entidadeId}")
    public ResponseEntity<?> deletarEntidade (@PathVariable Integer entidadeId) {
        log.info("[DELETAR ENTIDADE] - Iniciando processo para deleção da entidade.");
        entidadeInputPort.deletarEntidade(entidadeId);
        log.info("[DELETAR ENTIDADE] - Busca de todas as entidades realizada com sucesso");
        return ResponseEntity.ok().build();
    }
}
