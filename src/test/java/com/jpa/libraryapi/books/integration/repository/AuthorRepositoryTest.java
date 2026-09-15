package com.jpa.libraryapi.books.integration.repository;


import com.jpa.libraryapi.books.models.entities.Author;
import com.jpa.libraryapi.books.unit.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(new DockerImageName("postgres:16.0"));

    @Autowired
    AuthorRepository repository;

    @BeforeEach
    void setUp() {
        List<Author> authors = List.of(
                Author.builder()
                        .name("Leonardo Lopes")
                        .birthDate(LocalDate.of(2002, 9, 19))
                        .email("myemailaddress@gmail.com")
                        .phone("51912345678")
                        .nationality("Brazilian")
                        .build());

        repository.saveAll(authors);
    }

    @Test
    void connectionEstablished() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    void shouldReturnAuthorByName() {
        List<Author> authors = repository.findByName("Leonardo Lopes").orElseThrow();
        assertEquals("Leonardo Lopes", authors.getFirst().getName());
        assertEquals("Leonardo Lopes", authors.getLast().getName());
    }

    @Test
    void shouldNotReturnAuthorWhenNameIsNotFound() {
        List<Author> author = repository.findByName("Elon Musk").orElseThrow();
        assertThat(author.isEmpty()).isTrue();
    }

    @Test
    void shouldReturnAuthorByEmail() {
        Author author = (Author) repository.findByEmail("myemailaddress@gmail.com").orElseThrow();
        assertEquals("myemailaddress@gmail.com", author.getEmail());
    }
}
