package com.jpa.libraryapi.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpa.libraryapi.exceptions.RegistroDuplicadoException;
import com.jpa.libraryapi.model.Autor;
import com.jpa.libraryapi.repository.AutorRepository;

@Component
public class AutorValidator {
    
    @Autowired
    private AutorRepository repository;

    public void validar(Autor autor){
        if(existeAutor(autor)){
            throw new RegistroDuplicadoException("Autor já cadastrado");
        }
    }

    private boolean existeAutor(Autor autor){
        Optional<Autor> autorEncontrado = repository.findByNomeAndDataNascimentoAndNacionalidade
                                (autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade());
        
        if(autor.getId() == null){
            return autorEncontrado.isPresent();
        }

        return !autor.getId().equals(autorEncontrado.get().getId()) && autorEncontrado.isPresent();
    }
}
