package com.jpa.libraryapi.books.models.mapper;

import com.jpa.libraryapi.books.models.dtos.request.CreateCurriculumRequest;
import com.jpa.libraryapi.books.models.dtos.response.CurriculumResponse;
import com.jpa.libraryapi.books.models.entities.Curriculum;
import org.springframework.stereotype.Component;

@Component
public class CurriculumMapper {

    public Curriculum toEntity(CreateCurriculumRequest request) {
        return Curriculum.builder()
            .user(request.user())
            .attributes(request.attributes())
            .build();
    }

    public CurriculumResponse toDTO(Curriculum curriculum) {
        return new CurriculumResponse(curriculum.getAttributes());
    }

}
