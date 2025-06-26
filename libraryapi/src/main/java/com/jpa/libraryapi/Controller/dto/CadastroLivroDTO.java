package com.jpa.libraryapi.Controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.validator.constraints.ISBN;

import com.jpa.libraryapi.model.GeneroLivro;
import com.jpa.libraryapi.model.Livro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record CadastroLivroDTO(
    @ISBN
    @NotBlank(message = "campo obrigatório")
    String isbn,
    @NotBlank(message = "campo obrigatório")
    String titulo,
    @NotNull(message = "campo obrigatório")
    @Past(message = "campo obrigatório")
    LocalDate dataPublicacao,
    GeneroLivro genero,
    BigDecimal preco,
    @NotNull(message = "campo obrigatório")
    UUID idAutor) {

    public Livro fromEntity(){
        Livro livro = new Livro();
        livro.setIsbn(this.isbn);
        livro.setTitulo(this.titulo);
        livro.setDataPublicacao(this.dataPublicacao);
        livro.setGenero(this.genero);
        livro.setPreco(this.preco);
        livro.setId(this.idAutor);

        return livro;
    }
    
}
