package com.api.batuque.config.security;

import com.api.batuque.adpater.output.database.usuarioJpa.UsuarioJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    private static final PasswordEncoder passwordEnconder = new Argon2PasswordEncoder(
            16, // salt length em bytes
            32, // hash length em bytes
            1, // parallelism (threads)
            65536, // memória em KiB (64MB)
            3 // iterações
    );

    @Autowired
    private UsuarioJpaRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }


    public static String registerUser(String password) {
        return passwordEnconder.encode(password);
    }

    public boolean authenticateUser(String password, String storeHashFromDb) {
        return passwordEnconder.matches(password, storeHashFromDb);
    }
}
