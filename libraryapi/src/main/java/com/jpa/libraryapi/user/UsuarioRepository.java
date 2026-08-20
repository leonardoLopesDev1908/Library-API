package com.jpa.libraryapi.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{
    
    Usuario findByLogin(String login);

    Usuario findByEmail(String email);
}
