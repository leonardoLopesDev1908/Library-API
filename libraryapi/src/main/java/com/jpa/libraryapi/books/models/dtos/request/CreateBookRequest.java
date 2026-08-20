package com.jpa.libraryapi.books.models.dtos.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.jpa.libraryapi.books.models.entities.BookGenre;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateBookRequest (
    @NotBlank(message = "ISBN must not be blank")
    String isbn,

    @NotBlank(message = "Title must not be blank")
    String title,

    @NotBlank(message = "Genre must not be blank")
    BookGenre genre,

    @NotNull(message = "Publication date must no be null")
    LocalDate publicationDate,

    @NotBlank(message = "price must not be blank")
    @Min(value = 1) 
    @Max(value = 8)
    BigDecimal price,

    @NotNull(message = "The book must have a registered autor")
    UUID autorId
){}