package com.jpa.libraryapi.books.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jpa.libraryapi.books.models.entities.Author;
import com.jpa.libraryapi.books.models.mapper.AuthorMapper;
import com.jpa.libraryapi.books.repository.AuthorRepository;
import com.jpa.libraryapi.exceptions.OperacaoNaoPermitidaException;
import com.jpa.libraryapi.security.SecurityService;

import jakarta.persistence.EntityNotFoundException;


@Service
public class AuthorService {

    private final AuthorRepository repository;
    //private final SecurityService securityService;
    private final AuthorMapper mapper;

    public AuthorService(AuthorRepository repository, SecurityService securityService,
                         AuthorMapper mapper) {
        this.repository = repository;
        //this.securityService = securityService;
        this.mapper = mapper;
    }


    public Author salvar(Author author){
        return this.repository.save(author);
    }

    public void atualizar(Author author){
        if(author.getId()==null){
            throw new IllegalArgumentException("Nenhum autor com esse Id foi encontrado");
        }
        this.repository.save(author);
    }


    public Author obterPorId(UUID id){
        return repository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException();
        });
    }

    public void deletar(Author author){
        if(author.hasBook()){
            throw new OperacaoNaoPermitidaException("Sem permissão. Autor possui livros cadastrados!");
        }
        repository.delete(author);
    }

    public List<Author> pesquisa(String nome, String nacionalidade){
        if(nome != null && nacionalidade != null){
            return repository.findByNameAndNationality(nome, nacionalidade);
        } else if (nome != null){
            return repository.findByName(nome);
        } else if (nacionalidade != null){
            return repository.findByNationality(nacionalidade);
        }
        return repository.findAll();
    }
}
