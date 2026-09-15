package com.jpa.libraryapi.books.unit.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorRepositoryTest {

    @Test
    void findByName() {
    }

    @Test
    void findByNationality() {
    }

    @Test
    void findByNameAndNationality() {
    }

    @Test
    void findByNameOrNationalityLike() {
    }

    @Test
    void findByNameAndBirthDateAndNationality() {
    }
}