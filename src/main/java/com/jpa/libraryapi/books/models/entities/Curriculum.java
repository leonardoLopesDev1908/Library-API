package com.jpa.libraryapi.books.models.entities;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.UUID;

@Getter
@Builder
@Document(collection = "curriculum")
public class Curriculum {
    @Id private String id;

    private UUID user;

    private HashMap<String, String> attributes;
}
