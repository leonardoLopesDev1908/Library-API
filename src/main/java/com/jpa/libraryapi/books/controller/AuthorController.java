package com.jpa.libraryapi.books.controller;

import java.util.UUID;

import com.jpa.libraryapi.books.models.mapper.AuthorMapper;
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
    public ApiResponse<AuthorResponse> save(@RequestBody @Valid CreateAuthorRequest dto){
        AuthorResponse response = mapper.toDTO(service.salvar(mapper.toEntity(dto)));
        return ApiResponse.success(response);
    }

    @GetMapping("{id}")
    public ApiResponse<AuthorResponse> getDetails(@PathVariable("id") String id){
        var authorId = UUID.fromString(id);
        AuthorResponse response = mapper.toDTO(service.obterPorId(authorId));
        return ApiResponse.success(response);
    }

    // @DeleteMapping("{id}")
    // @PreAuthorize("hasRole('GERENTE')")
    // public ResponseEntity<Void> deletarAutor(@PathVariable("id") String id){
    //     var idAutor = UUID.fromString(id);
    //     Optional<Autor> autorOptional = service.obterPorId(idAutor);

    //     if(autorOptional.isEmpty()){
    //         return ResponseEntity.notFound().build();
    //     }

    //     service.deletar(autorOptional.get());
    //     return ResponseEntity.noContent().build();
    // }

    // @PutMapping("{id}")
    // @PreAuthorize("hasRole('GERENTE')")
    // public ResponseEntity<Void> atualizar(@PathVariable("id") String id, 
    //                                         @RequestBody CreateAutorRequest dto){
    //     AutorResponse response = service.update(id, dto);
    //     return ApiResponse.success(response);                                
    // }
}
