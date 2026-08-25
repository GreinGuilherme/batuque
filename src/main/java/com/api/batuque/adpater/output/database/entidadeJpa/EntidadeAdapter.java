package com.api.batuque.adpater.output.database.entidadeJpa;

import com.api.batuque.adpater.output.database.entidadeJpa.entity.EntidadeEntity;
import com.api.batuque.adpater.output.database.entidadeJpa.mapper.EntidadeEntityMapper;
import com.api.batuque.domain.enums.LinhaEntidadeEnum;
import com.api.batuque.domain.model.Entidades;
import com.api.batuque.domain.port.output.EntidadeRepositoryOutputPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class EntidadeAdapter implements EntidadeRepositoryOutputPort {

    private final EntidadeJpaRepository entidadeJpaRepository;
    private final EntidadeEntityMapper entidadeEntityMapper;

    public void incluirEntidade(Entidades entidade) {
        log.info("[SALVAR ENTIDADE] - Salvando entidade {}", entidade.getNomeEntidade());
        EntidadeEntity entity = entidadeEntityMapper.modelToDto(entidade);
        entidadeJpaRepository.save(entity);
        log.info("[SALVAR ENTIDADE] - Entidade {}, foi salvo com sucesso!", entidade.getNomeEntidade());
    }

    public List<Entidades> buscarEntidades() {
        log.info("[BUSCAR ENTIDADE] - Buscando todas as entidades");
        List<EntidadeEntity> entities = entidadeJpaRepository.findAll();
        log.info("[BUSCAR ENTIDADE] - Busca por todas todas as entidades realizada com sucesso!");
        return entidadeEntityMapper.entityListtoModelList(entities);
    }

    public Entidades buscarEntidadePorNome(String nome) {
        log.info("[BUSCAR ENTIDADE] - Buscando entidade: {}", nome);
        return entidadeJpaRepository.findByNomeEntidade(nome)
                .map(entity -> entidadeEntityMapper.dtoToModel(entity))
                .orElseThrow(() -> new EntityNotFoundException("[BUSCAR ENTIDADE] - Entidade não encontrada: " + nome));
    }

    @Override
    public Entidades buscarEntidadesPorId(Integer entidadeId) {
        log.info("[FILTRAR ENTIDADE] - Buscando entidade por ID: {}", entidadeId);
        return entidadeJpaRepository.findById(entidadeId)
                .map(entidadeEntityMapper::dtoToModel)
                .orElseThrow(() -> new EntityNotFoundException("[BUSCAR ENTIDADE] - Entidade não encontrada para o ID: " + entidadeId));
    }

    @Override
    public List<Entidades> buscarEntidadesPorFalange(String falange) {
        log.info("[FILTRAR ENTIDADE] - Filtrando entidade por falange: {}", falange);
        List<EntidadeEntity> entities = entidadeJpaRepository.findByFalange(falange);
        if (entities.isEmpty()) {
            throw new EntityNotFoundException("[BUSCAR ENTIDADE] - Nenhuma entidade encontrada para a falange: " + falange);
        }
        return entidadeEntityMapper.entityListtoModelList(entities);
    }

    @Override
    public List<Entidades> buscarEntidadesPorLinha(LinhaEntidadeEnum linhaEntidade) {
        log.info("[FILTRAR ENTIDADE] - Filtrando entidade por linha: {}", linhaEntidade);
        List<EntidadeEntity> entities = entidadeJpaRepository.findByLinha(linhaEntidade);
        if (entities.isEmpty()) {
            throw new EntityNotFoundException("[BUSCAR ENTIDADE] - Nenhuma entidade encontrada para a linha: " + linhaEntidade);
        }
        return entidadeEntityMapper.entityListtoModelList(entities);
    }

    @Override
    public void deletarEntidade(Integer entidadeId) {
        log.info("[DELETAR ENTIDADE] - Verificar existencia da entidade por ID: {}", entidadeId);
        var result = buscarEntidadesPorId(entidadeId);
        log.info("[DELETAR ENTIDADE] - Deletando entidade por ID: {}", entidadeId);
        entidadeJpaRepository.deleteById(entidadeId);
    }
}