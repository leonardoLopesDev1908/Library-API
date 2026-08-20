package com.jpa.libraryapi.books.service;

import com.jpa.libraryapi.books.models.entities.Author;
import com.jpa.libraryapi.books.models.mapper.AuthorMapper;
import com.jpa.libraryapi.books.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorServiceTest {

    @Mock
    private AuthorRepository repository;

    @Mock
    private AuthorMapper mapper;

    @InjectMocks
    private final AuthorService service;

    public AuthorServiceTest(AuthorService service) {
        this.service = service;
    }

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should save autor when everything and return its DTO")
    void salvar() {
        Author authorASalvar = Author.builder()
                .name("Leonardo Lopes")
                .birthDate(LocalDate.of(2025, 9, 23))
                .nationality("Argentinian")
                .build();

        Author authorSalvo = Author.builder()
                .id(UUID.randomUUID())
                .name("Leonardo Lopes")
                .birthDate(LocalDate.of(2025, 9, 23))
                .nationality("Argentinian")
                .build();

        when(repository.save(authorASalvar)).thenReturn(authorSalvo);

        Author resultado = service.salvar(authorASalvar);

        assertNotNull(resultado);
        assertNotNull(resultado.getId());
        assertEquals("Leonardo Lopes", resultado.getName());
        assertEquals("Argentinian", resultado.getNationality());

        verify(repository, times(1)).save(authorASalvar);
    }

    @Test
    void atualizar() {
    }

    @Test
    @DisplayName("Should return existing author")
    void successfullyFindById() {
        UUID id = UUID.randomUUID();
        Author savedAuthor = Author.builder()
                .id(id)
                .name("Leonardo Lopes")
                .birthDate(LocalDate.of(1999, 11, 12))
                .nationality("Brazilian")
                .build();

        when(repository.findById(id)).thenReturn(Optional.of(savedAuthor));

        Author searchedAuthor = service.obterPorId(id);

        assertNotNull(searchedAuthor);
        assertEquals(savedAuthor.getName(), searchedAuthor.getName());
        assertEquals(savedAuthor.getNationality(), searchedAuthor.getNationality());
        assertEquals(savedAuthor.getId(), searchedAuthor.getId());
        assertEquals(savedAuthor.getId(), searchedAuthor.getId());

        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException")
    void findByIdThrowException() {
        UUID wrongId = UUID.randomUUID();

        when(repository.findById(wrongId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> service.obterPorId(wrongId)
        );

        verify(repository, times(1)).findById(wrongId);
        verifyNoInteractions(repository);
    }

    @Test
    void deletar() {
    }

    @Test
    void pesquisa() {
    }
}