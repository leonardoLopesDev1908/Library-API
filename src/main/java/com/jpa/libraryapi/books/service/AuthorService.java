package com.jpa.libraryapi.books.service;

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

public interface AuthorService {

    public Author salvar(Author author);

    public Author update(Author author);

    public Author obterPorId(UUID id);

    public Author getByEmail(String email);

    public void deletar(Author author);

    public void deleteById(UUID id);
}
