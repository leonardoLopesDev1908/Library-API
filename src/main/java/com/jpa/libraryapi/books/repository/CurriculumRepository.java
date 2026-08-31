package com.jpa.libraryapi.books.repository;

import com.jpa.libraryapi.books.models.entities.Curriculum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumRepository extends MongoRepository<Curriculum, Long> {
}
