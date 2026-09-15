package com.jpa.libraryapi.books.service;

import java.util.List;
import java.util.UUID;

import com.jpa.libraryapi.exceptions.NotAllowedOperationException;
import org.springframework.cache.annotation.Cacheable;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jpa.libraryapi.books.models.entities.Author;
import com.jpa.libraryapi.books.models.mapper.AuthorMapper;
import com.jpa.libraryapi.books.repository.AuthorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthorService {

    private final AuthorRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthorService(AuthorRepository repository, PasswordEncoder encoder,
                         AuthorMapper mapper) {
        this.repository = repository;
        this.passwordEncoder = encoder;
    }

    public Author salvar(Author author){
        String encoded = passwordEncoder.encode(author.getPassword());

        System.out.println(author.getPassword());
        System.out.println(encoded);

        author.setPassword(encoded);
        Author savedAuthor =  this.repository.save(author);

        return savedAuthor;
    }

    @Transactional
    public Author update(Author author){
        if(author.getId()==null){
            throw new IllegalArgumentException("Nenhum autor com esse Id foi encontrado");
        }
        return this.repository.save(author);
    }

    @Cacheable(value = "authors", key = "#id")
    public Author obterPorId(UUID id){
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Cacheable(value = "authors", key = "#email")
    public Author getByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }

    public void deletar(Author author){
        if(author.hasBook()){
            throw new NotAllowedOperationException("Sem permissão. Autor possui livros cadastrados!");
        }

        repository.delete(author);
    }

    public void deleteById(UUID id) {
        Author author = obterPorId(id);
        if(author.hasBook()){
            throw new NotAllowedOperationException("Sem permissão. Autor possui livros cadastrados!");
        }
        repository.deleteById(id);
    }
}
