package com.api.batuque.adpater.output.database.pontoJpa;

import com.api.batuque.adpater.output.database.pontoJpa.entity.ControlePontoEntity;
import com.api.batuque.adpater.output.database.pontoJpa.mapper.PontoEntityMapper;
import com.api.batuque.domain.model.PontoEntidade;
import com.api.batuque.domain.port.output.PontoRepositoryOutputPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
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
    public Optional<PontoEntidade> buscarPorId(Long id) {
        return pontoJpaRepository.findById(id.intValue())
                .map(pontoEntityMapper::dtoToModel);
    }

    @Override
    public List<PontoEntidade> buscarPontosPorFiltro(PontoEntidade request) {
        log.info("[BUSCAR PONTO] - Persisindo na busca de playlist...");
        Specification<ControlePontoEntity> spec = PontoSpecifications.comFiltro(request);
        List<ControlePontoEntity> entities = pontoJpaRepository.findAll(spec);
        return entities.stream()
                .map(pontoEntityMapper::dtoToModel)
                .toList();
    }

    @Override
    public void deletarPonto(Long id, String nomePonto, String nomeEntidade) {
        log.info("[DELETAR PONTO] - Iniciando processo para salvar o ponto");
        pontoJpaRepository.deleteById(id.intValue());
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
