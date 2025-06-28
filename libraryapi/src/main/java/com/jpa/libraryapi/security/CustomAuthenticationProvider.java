package com.jpa.libraryapi.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jpa.libraryapi.model.Usuario;
import com.jpa.libraryapi.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UsuarioService service;
    private final PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String senhaDigitada = authentication.getCredentials().toString();

        Usuario usuarioEncontrado = service.obterPorLogin(login);

        if(usuarioEncontrado == null){
            throw new UsernameNotFoundException("Usuario ou senha incorretos");
        }

        String senhaBcrypted = usuarioEncontrado.getSenha();

        boolean senhasMatchs = encoder.matches(senhaDigitada, senhaBcrypted);

        if(senhasMatchs){
            return new CustomAuthentication(usuarioEncontrado);
        } 
        
        throw new UsernameNotFoundException("Usuario ou senha incorretos");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }

}
