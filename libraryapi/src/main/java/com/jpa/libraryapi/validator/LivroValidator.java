package com.jpa.libraryapi.validator;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jpa.libraryapi.exceptions.CampoInvalidoException;
import com.jpa.libraryapi.exceptions.RegistroDuplicadoException;
import com.jpa.libraryapi.model.Livro;
import com.jpa.libraryapi.repository.LivroRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LivroValidator {
    
    private static final int ANO_EXIGENCIA_PRECO = 2020;
    private final LivroRepository repository;

    public void validar(Livro livro){
        if(existeLivroIsbn(livro)){
            throw new RegistroDuplicadoException("ISBN já cadastrado.");
        }

        if(isPrecoObrigatorioNulo(livro)){
            throw new CampoInvalidoException("preco", "Livros a partir de 2020 devem ter preço");
        }
    }

    private boolean isPrecoObrigatorioNulo(Livro livro){
        return livro.getPreco() == null &&
                livro.getDataPublicacao().getYear() >= ANO_EXIGENCIA_PRECO;
    }

    private boolean existeLivroIsbn(Livro livro){
        Optional<Livro> livroEncontrado = repository.findByIsbn(livro.getIsbn());
        
        if(livro.getId()==null){
            return livroEncontrado.isPresent();
        }

        return livroEncontrado  
                    .map(Livro::getId)
                    .stream()
                    .anyMatch(id -> !id.equals(livro.getId()));
    }
}
