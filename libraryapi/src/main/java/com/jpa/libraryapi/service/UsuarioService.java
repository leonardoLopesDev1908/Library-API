package com.jpa.libraryapi.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jpa.libraryapi.model.Usuario;
import com.jpa.libraryapi.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    
    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    public void salvar(Usuario user){
        var senha = user.getSenha();
        user.setSenha(encoder.encode(senha));
        repository.save(user);
    }

    public Usuario obterPorLogin(String login){
        return repository.findByLogin(login);
    }
}
