package com.jpa.libraryapi.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.jpa.libraryapi.user.Usuario;
import com.jpa.libraryapi.user.UsuarioService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityService {
    
    private final UsuarioService service;

    public Usuario obterUsuarioLogado(){ 
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       
        if(authentication instanceof CustomAuthentication customAuth){
            return customAuth.getUsuario();
        }

        return null;
    }

}
