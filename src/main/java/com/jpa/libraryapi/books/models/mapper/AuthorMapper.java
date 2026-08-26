package com.jpa.libraryapi.books.models.mapper;

import java.time.LocalDateTime;

import com.jpa.libraryapi.books.models.dtos.request.CreateAuthorRequest;
import com.jpa.libraryapi.books.models.dtos.response.AuthorResponse;
import com.jpa.libraryapi.books.models.entities.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    
    public Author toEntity(CreateAuthorRequest request) {
        return Author.builder()
            .name(request.name())
            .nationality(request.nationality())
            .birthDate(request.birthDate())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }

    public AuthorResponse toDTO(Author author) {
        return new AuthorResponse(
            author.getName(),
            author.getBirthDate(),
            author.getNationality()
        );
    }
}
