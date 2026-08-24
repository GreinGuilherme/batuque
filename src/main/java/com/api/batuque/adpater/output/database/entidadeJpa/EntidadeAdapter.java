package com.api.batuque.adpater.output.database.entidadeJpa;

import com.api.batuque.adpater.output.database.entidadeJpa.entity.EntidadeEntity;
import com.api.batuque.adpater.output.database.entidadeJpa.mapper.EntidadeEntityMapper;
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
}