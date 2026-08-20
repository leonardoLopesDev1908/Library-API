package com.jpa.libraryapi.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.jpa.libraryapi.user.Usuario;
import com.jpa.libraryapi.user.UsuarioService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginSocialSuccesHandler extends SavedRequestAwareAuthenticationSuccessHandler{
    
    private final UsuarioService service;
    private final PasswordEncoder encoder;
    
    private static final String SENHA_PADRAO = "123";

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        
        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
        
        OAuth2User oauthUser = authToken.getPrincipal();
        String email = oauthUser.getAttribute("email");

        Usuario user = service.obterPorEmail(email);

        if (user == null) {
            user = cadastrarUsuario(email);
        }


        authentication = new UsernamePasswordAuthenticationToken(
            user.getLogin(),
            user.getSenha(),
            user.getRoles().stream().map(SimpleGrantedAuthority::new).toList()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        super.onAuthenticationSuccess(request, response, authentication);
    }

    public Usuario cadastrarUsuario(String email){
            Usuario user = new Usuario();
            user.setEmail(email);
            user.setLogin(tratarEmail(email));
            user.setRoles(List.of("FUNCIONARIO"));
            user.setSenha(encoder.encode(SENHA_PADRAO));
            
            service.salvar(user);
            return user;
    }

    public String tratarEmail(String email){
        return email.substring(0, email.indexOf('@'));
    }
}
