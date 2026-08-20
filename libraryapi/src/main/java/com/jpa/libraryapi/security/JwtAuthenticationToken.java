package com.jpa.libraryapi.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jpa.libraryapi.user.Usuario;
import com.jpa.libraryapi.user.UsuarioService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationToken extends OncePerRequestFilter {
    
    private final UsuarioService service;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, 
            HttpServletResponse response, 
            FilterChain filterChain) throws IOException, ServletException{
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(deveConverter(authentication)){
            String login = authentication.getName();
            Usuario usuario = service.obterPorLogin(login);
            if (usuario != null){
                authentication = new CustomAuthentication(usuario);
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }

    private boolean deveConverter(Authentication authentication){
        return authentication != null && authentication instanceof JwtAuthenticationToken;
    }
          
}


