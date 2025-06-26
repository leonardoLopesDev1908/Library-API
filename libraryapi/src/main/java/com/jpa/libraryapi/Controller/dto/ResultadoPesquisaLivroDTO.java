package com.jpa.libraryapi.Controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.jpa.libraryapi.model.GeneroLivro;

public record ResultadoPesquisaLivroDTO(
    String isbn,
    String titulo,
    LocalDate dataPublicacao,
    GeneroLivro genero,
    BigDecimal preco,
    AutorDTO autor){
    
}
