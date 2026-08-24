package com.api.batuque.domain.port.input;

import com.api.batuque.domain.model.PontoEntidade;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ControlePontoInputPort {
    void salvarPonto(PontoEntidade controlePonto);
    List<PontoEntidade> buscarPontos();
    List<PontoEntidade> buscarPontosPorNomeEntidade(String nomeEntidade);
    void deletarPontoPorEntidade(@RequestParam Integer id,
                                 @RequestParam String nomePonto,
                                 @RequestParam String nomeEntidade);
    void atualizarPonto(Integer id, PontoEntidade pontoEntidade);
}
