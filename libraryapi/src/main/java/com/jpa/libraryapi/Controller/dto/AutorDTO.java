package com.jpa.libraryapi.Controller.dto;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public record AutorDTO(UUID id,
                        @NotBlank(message="Campo obrigatório") //campo nem em branco nem nulo
                        @Size(max = 100, message="Máximo de 100 caracteres")
                        String nome,
                        @NotNull(message="Campo obrigatório") 
                        @Past(message="Data inválida para esse campo")
                        LocalDate dataNascimento, 
                        @NotBlank(message="Campo obrigatório") 
                        @Size(max = 50, min = 2, message="Máximo de 50 e mínimo de 2 caracteres")
                        String nacionalidade) {
               
}
