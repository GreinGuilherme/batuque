package com.api.batuque.adpater.input.gira;

import com.api.batuque.adpater.input.gira.dto.GiraFiltroRequest;
import com.api.batuque.adpater.input.gira.dto.GiraRequest;
import com.api.batuque.adpater.input.gira.dto.GiraResponse;
import com.api.batuque.adpater.input.gira.mapper.GiraRequestMapper;
import com.api.batuque.domain.enums.LinhaEntidadeEnum;
import com.api.batuque.domain.model.Gira;
import com.api.batuque.domain.model.GiraFiltro;
import com.api.batuque.domain.port.input.ControleGiraInputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/gira")
public class GestaoGiraController {

    private final ControleGiraInputPort controleGira;
    private final GiraRequestMapper giraRequestMapper;

    @PostMapping(value = "/cadastrar", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cadastrarGira (@RequestBody GiraRequest request) {
        log.info("[SALVAR GIRA] - Iniciando processo para salvar gira: {}", request.getNomeGira());
        var response = giraRequestMapper.dtoToModel(request);
        controleGira.salvarGira(response);
        log.info("[SALVAR GIRA] - Gira salva com sucesso");
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/buscar")
    public ResponseEntity<List<GiraResponse>> buscarTodasGira() {
        log.info("[BUSCAR GIRA] - Iniciando processo para buscar todas as gira.");
        List<Gira> response = controleGira.buscarGira();
        log.info("[BUSCAR GIRA] - Busca de todos as gira realizada com sucesso");
        List<GiraResponse> responselist = giraRequestMapper.modelListToDtolist(response);
        return ResponseEntity.ok().body(responselist);
    }

    @GetMapping(value = "/buscar/filtrar")
    public ResponseEntity<List<GiraResponse>> buscarPontosPorEntidade(@RequestParam(required = false) Long giraId,
                                                                      @RequestParam(required = false) String giraNome,
                                                                      @RequestParam(required = false) Long entidadeId,
                                                                      @RequestParam(required = false) Long pontoId,
                                                                      @RequestParam(required = false) LinhaEntidadeEnum linhaEntidade,
                                                                      @RequestParam(required = false) String nomePonto,
                                                                      @RequestParam(required = false) String nomeEntidade,
                                                                      @RequestParam(required = false) String pontoLetra) {
        log.info("[BUSCAR GIRA] - Iniciando processo para buscar pontos da entidade.");
        GiraFiltroRequest request = new GiraFiltroRequest(giraId, giraNome, entidadeId, pontoId, linhaEntidade, nomePonto, nomeEntidade, pontoLetra);
        GiraFiltro domain = giraRequestMapper.filtroToDomain(request);
        List<Gira> response = controleGira.buscarGiraPorFiltro(domain);
        log.info("[BUSCAR GIRA] - Busca de pontos realizada com sucesso");
        List<GiraResponse> responselist = giraRequestMapper.modelListToDtolist(response);
        return ResponseEntity.ok().body(responselist);
    }

    @DeleteMapping(value = "/deletar")
    public ResponseEntity<?> deletarGiraPorId(@RequestParam Long id,
                                                  @RequestParam(required = false) String nomePonto,
                                                  @RequestParam(required = false) String nomeEntidade) {
        log.info("[DELETAR GIRA] - Iniciando processo para deletar gira: {} da entidade: {}.", nomePonto, nomeEntidade);
        controleGira.deletarGiraPorId(id, nomePonto, nomeEntidade);
        log.info("[DELETAR GIRA] - Deleção de gira: {} da entidade: {} realizada com sucesso.", nomePonto, nomeEntidade);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/atualizar/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> altualizarGira (@PathVariable Long id,
                                                 @RequestBody GiraRequest request) {
        log.info("[ATUALIZAR GIRA] - Iniciando processo para atualizar o gira: {}", request.getNomeGira());
        var response = giraRequestMapper.dtoToModel(request);
        controleGira.atualizarGira(id, response);
        log.info("[ATUALIZAR GIRA] - Atualização realizada com sucesso");
        return ResponseEntity.ok().build();
    }
}
