package com.jpa.libraryapi.Controller.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
            @NotBlank(message="campo obrigatorio")
            String login, 
            @NotBlank(message="campo obrigatorio")
            String senha, 
            @Email(message="invalido") 
            @NotBlank(message="campo obrigatorio")
            String email, 
            List<String> roles) {
    
}
