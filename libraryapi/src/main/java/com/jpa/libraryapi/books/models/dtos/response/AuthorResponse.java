package com.jpa.libraryapi.books.models.dtos.response;

import java.time.LocalDate;

public record AuthorResponse(
    String name,
    LocalDate birthDate,
    String nationality
) {}
