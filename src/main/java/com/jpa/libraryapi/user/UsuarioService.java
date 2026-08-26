package com.jpa.libraryapi.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

//@Service
//@RequiredArgsConstructor
//public class UsuarioService {
//
//    private final UsuarioRepository repository;
////    private final PasswordEncoder encoder;
//
//    public void salvar(Usuario user){
//        user.setSenha(encoder.encode(user.getSenha()));
//        repository.save(user);
//    }
//
//    public Usuario obterPorLogin(String login){
//        return repository.findByLogin(login);
//    }
//
//    public Usuario obterPorEmail(String email){
//        return repository.findByEmail(email);
//    }
//}
