package com.jpa.libraryapi.books.controller;

import com.jpa.libraryapi.books.models.dtos.request.CreateCurriculumRequest;
import com.jpa.libraryapi.books.models.dtos.response.CurriculumResponse;
import com.jpa.libraryapi.books.models.mapper.CurriculumMapper;
import com.jpa.libraryapi.books.service.CurriculumService;
import com.jpa.libraryapi.common.ApiConstants;
import com.jpa.libraryapi.common.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.API_CURRICULUM)
public class CurriculumController {

    private final CurriculumService service;
    private final CurriculumMapper mapper;

    public CurriculumController(CurriculumService service, CurriculumMapper mapper) {
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping
    public ApiResponse<CurriculumResponse> create(@RequestBody CreateCurriculumRequest dto) {
        CurriculumResponse response = mapper.toDTO(service.create(mapper.toEntity(dto)));
        return ApiResponse.success(response);
    }

}
