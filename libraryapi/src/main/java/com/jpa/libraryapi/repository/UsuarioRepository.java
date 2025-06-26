package com.jpa.libraryapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.libraryapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{
    
    Usuario findByLogin(String login);
}
