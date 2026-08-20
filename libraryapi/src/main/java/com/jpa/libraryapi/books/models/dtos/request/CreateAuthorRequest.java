package com.jpa.libraryapi.books.models.dtos.request;


import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAuthorRequest(
    @NotBlank(message = "Name must not be blank")
    String name,

    @NotNull(message = "Birth date must not be null")
    LocalDate birthDate,
    
    @NotBlank(message = "Nationality must not be blank")
    String nationality
){}
