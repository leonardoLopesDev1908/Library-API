package com.jpa.libraryapi.books.controller;

import java.util.UUID;

import com.jpa.libraryapi.books.models.mapper.AuthorMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.libraryapi.books.models.dtos.request.CreateAuthorRequest;
import com.jpa.libraryapi.books.models.dtos.response.AuthorResponse;
import com.jpa.libraryapi.books.service.AuthorService;
import com.jpa.libraryapi.common.ApiResponse;

import com.jpa.libraryapi.common.ApiConstants;
import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiConstants.API_AUTHOR)
public class AuthorController {
 
    private final AuthorService service;
    private final AuthorMapper mapper;

    public AuthorController(AuthorService service, AuthorMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AuthorResponse>> save(@RequestBody @Valid CreateAuthorRequest dto){
        AuthorResponse response = mapper.toDTO(service.salvar(mapper.toEntity(dto)));
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<AuthorResponse>> getDetails(@PathVariable("id") UUID id){
        AuthorResponse response = mapper.toDTO(service.obterPorId(id));
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(response));
    }
}
