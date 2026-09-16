package com.jpa.libraryapi.books.service;

import com.jpa.libraryapi.books.models.entities.Curriculum;
import com.jpa.libraryapi.books.repository.CurriculumRepository;
import org.springframework.stereotype.Service;

@Service
public class CurriculumService {

    private final CurriculumRepository repository;

    public CurriculumService(CurriculumRepository repository) {
        this.repository = repository;
    }

    public Curriculum create(Curriculum request) {
        return repository.save(request);
    }
}
