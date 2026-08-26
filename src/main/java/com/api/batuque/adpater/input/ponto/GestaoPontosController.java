package com.api.batuque.adpater.input.ponto;

import com.api.batuque.adpater.input.ponto.dto.PontoFiltroRequest;
import com.api.batuque.adpater.input.ponto.dto.PontoRequest;
import com.api.batuque.adpater.input.ponto.dto.PontoResponse;
import com.api.batuque.adpater.input.ponto.mapper.PontoFiltroRequestMapper;
import com.api.batuque.adpater.input.ponto.mapper.PontoRequestMapper;
import com.api.batuque.domain.model.PontoEntidade;
import com.api.batuque.domain.port.input.ControlePontoInputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/gestaopontos")
public class GestaoPontosController {

    private final ControlePontoInputPort controlePonto;
    private final PontoRequestMapper pontoRequestMapper;
    private final PontoFiltroRequestMapper pontoFiltroRequestMapper;

    @PostMapping(value = "/cadastrar", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cadastrarPonto (@RequestBody PontoRequest request) {
        log.info("[SALVAR PONTO] - Iniciando processo para salvar o ponto");
        var response = pontoRequestMapper.dtoToModel(request);
        controlePonto.salvarPonto(response);
        log.info("[SALVAR PONTO] - Inclusão realizada com sucesso");
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/buscar")
    public ResponseEntity<List<PontoResponse>> buscarTodosPontos() {
        log.info("[BUSCAR PONTO] - Iniciando processo para buscar todos os pontos.");
        List<PontoEntidade> response = controlePonto.buscarPontos();
        log.info("[BUSCAR PONTO] - Busca de todos os pontos realizada com sucesso");
        List<PontoResponse> responselist = pontoRequestMapper.modelListToDtolist(response);
        return ResponseEntity.ok().body(responselist);
    }

    @GetMapping(value = "/buscar/filtro")
    public ResponseEntity<List<PontoResponse>> buscarPontosPorEntidade(@ModelAttribute PontoFiltroRequest request) {
        log.info("[BUSCAR PONTO] - Iniciando processo para buscar pontos da entidade.");
        PontoEntidade domain = pontoFiltroRequestMapper.dtoToModel(request);
        List<PontoEntidade> response = controlePonto.buscarPontosPorFiltro(domain);
        log.info("[BUSCAR PONTO] - Busca de pontos realizada com sucesso");
        List<PontoResponse> responselist = pontoRequestMapper.modelListToDtolist(response);
        return ResponseEntity.ok().body(responselist);
    }

    @DeleteMapping(value = "/deletar")
    public ResponseEntity<?> deletarPontoPorEntidade(@RequestParam Long id,
                                                     @RequestParam String nomePonto,
                                                     @RequestParam String nomeEntidade) {
        log.info("[DELETAR PONTO] - Iniciando processo para deletar ponto: {} da entidade: {}.", nomePonto, nomeEntidade);
        controlePonto.deletarPontoPorEntidade(id, nomePonto, nomeEntidade);
        log.info("[DELETAR PONTO] - Deleção de ponto: {} da entidade: {} realizada com sucesso.", nomePonto, nomeEntidade);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/atualizar/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> altualizarPonto (@PathVariable Long id,
                                              @RequestBody PontoRequest request) {
        log.info("[ATUALIZAR PONTO] - Iniciando processo para atualizar o ponto: {}", request.getNomePonto());
        var response = pontoRequestMapper.dtoToModel(request);
        controlePonto.atualizarPonto(id, response);
        log.info("[ATUALIZAR PONTO] - Atualização realizada com sucesso");
        return ResponseEntity.ok().build();
    }
}
