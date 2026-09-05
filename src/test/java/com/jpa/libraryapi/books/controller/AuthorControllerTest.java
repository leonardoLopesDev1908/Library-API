package com.jpa.libraryapi.books.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpa.libraryapi.books.models.dtos.request.CreateAuthorRequest;
import com.jpa.libraryapi.books.models.dtos.response.AuthorResponse;
import com.jpa.libraryapi.books.models.entities.Author;
import com.jpa.libraryapi.books.models.mapper.AuthorMapper;
import com.jpa.libraryapi.books.service.AuthorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthorService service;

    @MockitoBean
    private ObjectMapper objectMapper;

    @MockitoBean
    private AuthorMapper mapper;

    @Test
    @DisplayName("Should return 202 created")
    void save() throws Exception {
        CreateAuthorRequest request = new CreateAuthorRequest(
                "Leonardo Lopes",
                LocalDate.of(2002, 8, 19),
                "Brazilian"
        );

        UUID id = UUID.randomUUID();
        Author author = Author.builder()
                .id(id)
                .name("Leonardo Lopes")
                .birthDate(LocalDate.of(2002, 8, 19))
                .nationality("Brazilian")
                .build();

        Mockito.when(service.salvar(Mockito.any(Author.class))).thenReturn(author);

        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(request.name()))
                .andExpect(jsonPath("$.birthDate").value("2002-08-19"))
                .andExpect(jsonPath("$.nationality").value(request.nationality()));
    }

    @Test
    void getDetails() {

    }
}
