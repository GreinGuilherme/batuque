package com.api.batuque.adpater.input.playlist;

import com.api.batuque.adpater.input.playlist.dto.PlaylistRequest;
import com.api.batuque.adpater.input.playlist.dto.PlaylistResponse;
import com.api.batuque.adpater.input.playlist.mapper.PlaylistRequestMapper;
import com.api.batuque.domain.model.Playlist;
import com.api.batuque.domain.port.input.ControlePlaylistInputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/playlist")
public class GestaoPlaylistController {

    private final ControlePlaylistInputPort controlePlaylist;
    private final PlaylistRequestMapper playlistRequestMapper;

    @PostMapping(value = "/cadastrar", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cadastrarPlaylist (@RequestBody PlaylistRequest request) {
        log.info("[SALVAR PLAYLIST] - Iniciando processo para salvar playlist: {}", request.getNomePlaylist());
        var response = playlistRequestMapper.dtoToModel(request);
        controlePlaylist.salvarPlaylist(response);
        log.info("[SALVAR PLAYLIST] - Playlist salva com sucesso");
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/buscar")
    public ResponseEntity<List<PlaylistResponse>> buscarTodosPontos() {
        log.info("[BUSCAR PLAYLIST] - Iniciando processo para buscar todas as playlist.");
        List<Playlist> response = controlePlaylist.buscarPlaylist();
        log.info("[BUSCAR PLAYLIST] - Busca de todos as playlist realizada com sucesso");
        List<PlaylistResponse> responselist = playlistRequestMapper.modelListToDtolist(response);
        return ResponseEntity.ok().body(responselist);
    }

//    @GetMapping(value = "/buscar/filtro")
//    public ResponseEntity<PlaylistResponse> buscarPontosPorEntidade(@ModelAttribute PlaylistFiltroRequest request) {
//        log.info("[BUSCAR PONTO] - Iniciando processo para buscar pontos da entidade.");
//        PontoEntidade domain = playlistRequestMapper.dtoToModel(request);
//        List<PontoEntidade> response = controlePlaylist.buscarPlaylistPorFiltro(domain);
//        log.info("[BUSCAR PONTO] - Busca de pontos realizada com sucesso");
//        List<PontoResponse> responselist = playlistRequestMapper.modelListToDtolist(response);
//        return ResponseEntity.ok().body(responselist);
//    }
//
//    @DeleteMapping(value = "/deletar")
//    public ResponseEntity<?> deletarPontoPorEntidade(@RequestParam Long id,
//                                                     @RequestParam String nomePonto,
//                                                     @RequestParam String nomeEntidade) {
//        log.info("[DELETAR PONTO] - Iniciando processo para deletar ponto: {} da entidade: {}.", nomePonto, nomeEntidade);
//        controlePlaylist.deletarPlaylistPorEntidade(id, nomePonto, nomeEntidade);
//        log.info("[DELETAR PONTO] - Deleção de ponto: {} da entidade: {} realizada com sucesso.", nomePonto, nomeEntidade);
//        return ResponseEntity.ok().build();
//    }
//
//    @PatchMapping(value = "/atualizar/{id}", produces = APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> altualizarPonto (@PathVariable Long id,
//                                              @RequestBody PontoRequest request) {
//        log.info("[ATUALIZAR PONTO] - Iniciando processo para atualizar o ponto: {}", request.getNomePonto());
//        var response = playlistRequestMapper.dtoToModel(request);
//        controlePlaylist.atualizarPlaylist(id, response);
//        log.info("[ATUALIZAR PONTO] - Atualização realizada com sucesso");
//        return ResponseEntity.ok().build();
//    }
}
