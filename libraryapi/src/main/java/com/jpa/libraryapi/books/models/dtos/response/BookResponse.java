package com.jpa.libraryapi.books.models.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jpa.libraryapi.books.models.entities.BookGenre;

public record BookResponse (
    String isbn,
    String title,
    BookGenre genre,
    LocalDate publicationDate,
    BigDecimal preco,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
){}