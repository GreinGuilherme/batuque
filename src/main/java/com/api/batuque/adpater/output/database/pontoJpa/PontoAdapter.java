package com.api.batuque.adpater.output.database.pontoJpa;

import com.api.batuque.adpater.output.database.pontoJpa.entity.ControlePontoEntity;
import com.api.batuque.adpater.output.database.pontoJpa.mapper.PontoEntityMapper;
import com.api.batuque.domain.enums.LinhaEntidadeEnum;
import com.api.batuque.domain.model.PontoEntidade;
import com.api.batuque.domain.port.output.PontoRepositoryOutputPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class PontoAdapter implements PontoRepositoryOutputPort {

    private final PontoJpaRepository pontoJpaRepository;
    private final PontoEntityMapper pontoEntityMapper;

    @Transactional
    public void incluirPonto(PontoEntidade pontoEntidade) {
        log.info("[SALVAR PONTO] - Iniciando processo para salvar o ponto");
        ControlePontoEntity ponto = pontoEntityMapper.modelToDto(pontoEntidade);
        pontoJpaRepository.save(ponto);
        log.info("[SALVAR PONTO] - Ponto foi salvo com sucesso!");
    }

    @Override
    public List<PontoEntidade> buscarPontos() {
        log.info("[BUSCAR PONTO] - Buscando todos os pontos");
        List<ControlePontoEntity> pontos = pontoJpaRepository.findAll();
        log.info("[BUSCAR PONTO] - Busca por todtodos os pontos realizadas com sucesso!");
        return pontoEntityMapper.entityListtoModelList(pontos);
    }

    @Override
    public Optional<PontoEntidade> buscarPorId(Integer id) {
        return pontoJpaRepository.findById(id)
                .map(pontoEntityMapper::dtoToModel);
    }

    @Override
    public List<PontoEntidade> buscarPontosPorNomePontos(String nomePonto) {
        log.info("[BUSCAR PONTO] - Buscando ponto com nome: {}", nomePonto);
        List<ControlePontoEntity> pontos = pontoJpaRepository.buscarPorNomePonto(nomePonto);
        return pontoEntityMapper.entityListtoModelList(pontos);
    }

    @Override
    public List<PontoEntidade> buscarPontosPorPontoLetra(String pontoLetra) {
        log.info("[BUSCAR PONTO] - Buscando ponto pela letra: {}", pontoLetra);
        List<ControlePontoEntity> pontos = pontoJpaRepository.buscarPorNomePonto(pontoLetra);
        return pontoEntityMapper.entityListtoModelList(pontos);
    }

    @Override
    public List<PontoEntidade> buscarPontosNomeEntidade(String nomeEntidade) {
        log.info("[BUSCAR PONTO] - Buscando pontos associados ao nome da entidade: {}", nomeEntidade);
        List<ControlePontoEntity> pontos = pontoJpaRepository.buscarPorNomeEntidade(nomeEntidade);
        return pontoEntityMapper.entityListtoModelList(pontos);
    }

    @Override
    public List<PontoEntidade> buscarPontosPorLinhaEntidade(LinhaEntidadeEnum linhaEntidade) {
        log.info("[BUSCAR PONTO] - Buscando pontos associados à linha da entidade: {}", linhaEntidade.getDescrition());
        List<ControlePontoEntity> pontos = pontoJpaRepository.buscarPorLinhaEntidade(linhaEntidade);
        return pontoEntityMapper.entityListtoModelList(pontos);
    }

    @Override
    public void deletarPonto(Integer id, String nomePonto, String nomeEntidade) {
        log.info("[DELETAR PONTO] - Iniciando processo para salvar o ponto");
        pontoJpaRepository.deleteById(id);
        log.info("[DELETAR PONTO] - Ponto foi deletado com sucesso!");
    }

    @Override
    public void atualizarPonto(PontoEntidade pontoEntidade) {
        log.info("[ATUALIZAR PONTO] - Processo para atualizar o ponto: {}, foi iniciado.", pontoEntidade.getNomePonto());
        ControlePontoEntity entity = pontoEntityMapper.modelToDto(pontoEntidade);
        pontoJpaRepository.save(entity);
        log.info("[ATUALIZAR PONTO] - Ponto salvo com sucesso!");
    }
}
