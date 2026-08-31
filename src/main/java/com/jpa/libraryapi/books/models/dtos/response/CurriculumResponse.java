package com.jpa.libraryapi.books.models.dtos.response;

import java.util.HashMap;

public record CurriculumResponse (
    HashMap<String, String> attributes
){}
