package com.api.batuque.adpater.output.database.usuarioJpa;

import com.api.batuque.adpater.output.database.usuarioJpa.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {
    UserDetails findByEmail(String email);
}
