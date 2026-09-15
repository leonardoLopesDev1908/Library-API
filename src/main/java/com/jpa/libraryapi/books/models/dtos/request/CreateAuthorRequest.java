package com.jpa.libraryapi.books.models.dtos.request;


import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAuthorRequest(
    @NotBlank(message = "Name must not be blank")
    String name,

    @NotBlank(message= "Invalid email")
    @Email
    String email,

    @NotBlank()
    @Size(min = 8)
    String password,

    @NotBlank(message = "Invalid phone number")
    String phone,

    @NotNull(message = "Birth date must not be null")
    LocalDate birthDate,
    
    @NotBlank(message = "Nationality must not be blank")
    String nationality
){}
