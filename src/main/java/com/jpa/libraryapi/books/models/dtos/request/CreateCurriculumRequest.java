package com.jpa.libraryapi.books.models.dtos.request;


import java.util.HashMap;
import java.util.UUID;

public record CreateCurriculumRequest (
    UUID user,
    HashMap<String, String> attributes
){}
