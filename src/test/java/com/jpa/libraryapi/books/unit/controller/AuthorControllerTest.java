package com.jpa.libraryapi.books.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpa.libraryapi.books.models.dtos.request.CreateAuthorRequest;
import com.jpa.libraryapi.books.models.dtos.response.AuthorResponse;
import com.jpa.libraryapi.books.models.entities.Author;
import com.jpa.libraryapi.books.models.mapper.AuthorMapper;
import com.jpa.libraryapi.books.service.AuthorService;
import com.jpa.libraryapi.config.SecurityConfig;
import com.jpa.libraryapi.exceptions.BadRequestException;
import com.jpa.libraryapi.user.AuthorUserDetailsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AuthorController.class)
@Import(SecurityConfig.class)
@WithMockUser(username = "mockedemail@gmail.com", authorities = "AUTHOR")
class AuthorControllerTest {

    @MockitoBean
    private AuthorUserDetailsService userDetailsService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AuthorService service;

    @MockitoBean
    private AuthorMapper mapper;

    private CreateAuthorRequest request;

    private Author author;

    private AuthorResponse response;

//        "name": "Mock User",
//            "email": "mockedemail@gmail.com",
//            "phone": "1234556789",
//            "password": "809117Aa*",
//            "birthDate": "2000-01-01",
//            "nationality": "Brasileira"

    @BeforeEach
    void setup() {
        request = new CreateAuthorRequest(
                "Leonardo Lopes",
                "myemailaddress@gmail.com",
                "123456789#$%&**Abc",
                "11912345678",
                LocalDate.of(2002, 8, 19),
                "Brazilian"
        );

        UUID id = UUID.randomUUID();
        author = Author.builder()
                .id(id)
                .name("Leonardo Lopes")
                .email("myemailaddress@gmail.com")
                .password("123456789#$%&**Abc")
                .phone("11912345678")
                .birthDate(LocalDate.of(2002, 8, 19))
                .nationality("Brazilian")
                .build();

        response = new AuthorResponse(
                author.getName(),
                author.getBirthDate(),
                author.getNationality()
        );
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @DisplayName("Should return 202 created")
    void successfullySave() throws Exception {
        Mockito.when(mapper.toEntity(Mockito.any(CreateAuthorRequest.class))).thenReturn(author);
        Mockito.when(service.salvar(Mockito.any(Author.class))).thenReturn(author);
        Mockito.when(mapper.toDTO(author)).thenReturn(response);

        mockMvc.perform(post("/api/author")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.name").value(request.name()))
                .andExpect(jsonPath("$.data.birthDate").value("2002-08-19"))
                .andExpect(jsonPath("$.data.nationality").value(request.nationality()));
    }

    @Test
    @DisplayName("Should throw BadRequestException for a Author without name")
    void saveErrorBadRequestException() throws Exception {
        UUID id = UUID.randomUUID();
        Author author = Author.builder()
                .id(id)
                .name("")
                .email("leonardosilva@gmail.com")
                .password("")
                .phone("")
                .birthDate(LocalDate.of(2002, 8, 19))
                .nationality("Brazilian")
                .build();

        Mockito.when(service.salvar(author)).thenThrow(BadRequestException.class);

        mockMvc.perform(post("/api/author")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(author)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnAuthorDetails() throws Exception {
        Mockito.when(service.obterPorId(author.getId())).thenReturn(author);
        Mockito.when(mapper.toDTO(author)).thenReturn(response);

        mockMvc.perform(get("/api/author/{id}", author.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value(author.getName()));
    }

    @Test
    void getDetails() {

    }
}
