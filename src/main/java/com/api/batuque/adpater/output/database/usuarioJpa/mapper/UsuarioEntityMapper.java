package com.api.batuque.adpater.output.database.usuarioJpa.mapper;

import com.api.batuque.adpater.output.database.usuarioJpa.entity.UsuarioEntity;
import com.api.batuque.domain.model.RegistroUsuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioEntityMapper {

    UsuarioEntity modelToDto(RegistroUsuario entidades);
    RegistroUsuario dtoToModel(UsuarioEntity entidadeRequest);
}
