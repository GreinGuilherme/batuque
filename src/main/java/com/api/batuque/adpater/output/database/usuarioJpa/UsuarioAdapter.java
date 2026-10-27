package com.api.batuque.adpater.output.database.usuarioJpa;

import com.api.batuque.adpater.output.database.usuarioJpa.entity.UsuarioEntity;
import com.api.batuque.adpater.output.database.usuarioJpa.mapper.UsuarioEntityMapper;
import com.api.batuque.domain.Exception.BadRequestException;
import com.api.batuque.domain.Exception.NotFoundException;
import com.api.batuque.domain.model.RegistroUsuario;
import com.api.batuque.domain.port.output.AuthRepositoryOutputPort;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UsuarioAdapter implements AuthRepositoryOutputPort {

    private final UsuarioJpaRepository repository;
    private final UsuarioEntityMapper usuarioEntityMapper;

    @Override
    public RegistroUsuario findByEmail(RegistroUsuario usuario) {
        try{
        log.info("[CADASTRO LOGIN] - Persistido usuário no banco de dados...");
        UsuarioEntity responseDb = (UsuarioEntity) repository.findByEmail(usuario.getEmail());
        RegistroUsuario response = usuarioEntityMapper.dtoToModel(responseDb);
        return response;
        } catch (NoResultException e){
            throw new NotFoundException(e.getCause().toString());
        } catch (RuntimeException e) {
            throw new BadRequestException(e.getCause().toString());
        }
    }

    @Override
    public void save(RegistroUsuario registro) {
        log.info("[CADASTRO LOGIN] - Criando usuário...");
        UsuarioEntity novoUsuario = new UsuarioEntity(null, registro.getNome(), registro.getEmail(), null, registro.getRole(), null, null);

        log.info("[CADASTRO LOGIN] - Persistindo usuário...");
        repository.save(novoUsuario);
    }

    @Override
    public void save(RegistroUsuario registro, String senhaCriptografada) {
        log.info("[CADASTRO LOGIN] - Criando usuário...");
        UsuarioEntity novoUsuario = new UsuarioEntity(null, registro.getNome(), registro.getEmail(), senhaCriptografada, registro.getRole(), null, null);

        log.info("[CADASTRO LOGIN] - Persistindo usuário...");
        repository.save(novoUsuario);
    }
}
