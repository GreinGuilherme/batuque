package com.api.batuque.adpater.input.playlist;

import com.api.batuque.adpater.input.playlist.dto.PlaylistFiltroRequest;
import com.api.batuque.adpater.input.playlist.dto.PlaylistRequest;
import com.api.batuque.adpater.input.playlist.dto.PlaylistResponse;
import com.api.batuque.adpater.input.playlist.mapper.PlaylistRequestMapper;
import com.api.batuque.domain.enums.LinhaEntidadeEnum;
import com.api.batuque.domain.model.Playlist;
import com.api.batuque.domain.model.PlaylistFiltro;
import com.api.batuque.domain.port.input.ControlePlaylistInputPort;
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
    public ResponseEntity<List<PlaylistResponse>> buscarTodasPlaylist() {
        log.info("[BUSCAR PLAYLIST] - Iniciando processo para buscar todas as playlist.");
        List<Playlist> response = controlePlaylist.buscarPlaylist();
        log.info("[BUSCAR PLAYLIST] - Busca de todos as playlist realizada com sucesso");
        List<PlaylistResponse> responselist = playlistRequestMapper.modelListToDtolist(response);
        return ResponseEntity.ok().body(responselist);
    }

    @GetMapping(value = "/buscar/filtro")
    public ResponseEntity<List<PlaylistResponse>> buscarPontosPorEntidade(@RequestParam(required = false) Long playlistId,
                                                                          @RequestParam(required = false) String playlistNome,
                                                                          @RequestParam(required = false) Long entidadeId,
                                                                          @RequestParam(required = false) Long pontoId,
                                                                          @RequestParam(required = false) LinhaEntidadeEnum linhaEntidade,
                                                                          @RequestParam(required = false) String nomePonto,
                                                                          @RequestParam(required = false) String nomeEntidade,
                                                                          @RequestParam(required = false) String pontoLetra) {
        log.info("[BUSCAR PLAYLIST] - Iniciando processo para buscar pontos da entidade.");
        PlaylistFiltroRequest request = new PlaylistFiltroRequest(playlistId, playlistNome, entidadeId, pontoId, linhaEntidade, nomePonto, nomeEntidade, pontoLetra);
        PlaylistFiltro domain = playlistRequestMapper.filtroToDomain(request);
        List<Playlist> response = controlePlaylist.buscarPlaylistPorFiltro(domain);
        log.info("[BUSCAR PLAYLIST] - Busca de pontos realizada com sucesso");
        List<PlaylistResponse> responselist = playlistRequestMapper.modelListToDtolist(response);
        return ResponseEntity.ok().body(responselist);
    }

    @DeleteMapping(value = "/deletar")
    public ResponseEntity<?> deletarPlaylistPorId(@RequestParam Long id,
                                                  @RequestParam(required = false) String nomePonto,
                                                  @RequestParam(required = false) String nomeEntidade) {
        log.info("[DELETAR PLAYLIST] - Iniciando processo para deletar playlist: {} da entidade: {}.", nomePonto, nomeEntidade);
        controlePlaylist.deletarPlaylistPorId(id, nomePonto, nomeEntidade);
        log.info("[DELETAR PLAYLIST] - Deleção de playlist: {} da entidade: {} realizada com sucesso.", nomePonto, nomeEntidade);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/atualizar/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> altualizarPlaylist (@PathVariable Long id,
                                                 @RequestBody PlaylistRequest request) {
        log.info("[ATUALIZAR PLAYLIST] - Iniciando processo para atualizar o playlist: {}", request.getNomePlaylist());
        var response = playlistRequestMapper.dtoToModel(request);
        controlePlaylist.atualizarPlaylist(id, response);
        log.info("[ATUALIZAR PLAYLIST] - Atualização realizada com sucesso");
        return ResponseEntity.ok().build();
    }
}
