package com.jpa.libraryapi.books.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.jpa.libraryapi.books.models.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID>{

    Optional<List<Author>> findByName(String nome);

    Optional<Author> findByEmail(String email);

    List<Author> findByNationality(String nacionalidade);
    
    List<Author> findByNameAndNationality(String nome, String nacionalidade);

    List<Author> findByNameOrNationalityLike(String nome, String nacionalidade);

    Optional<Author> findByNameAndBirthDateAndNationality(String nome,
                                                          LocalDate dataNascimento,
                                                          String nacionalidade);
}
