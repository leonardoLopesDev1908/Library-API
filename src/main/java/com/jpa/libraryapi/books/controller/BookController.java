package com.jpa.libraryapi.books.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.libraryapi.books.models.dtos.request.CreateBookRequest;
import com.jpa.libraryapi.books.models.dtos.response.BookResponse;
import com.jpa.libraryapi.books.service.BookService;
import com.jpa.libraryapi.common.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("livros")
public class BookController {
    
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('OPERADOR', 'GERENTE')")
    public ApiResponse<BookResponse> salvar(@RequestBody @Valid CreateBookRequest dto){
        BookResponse response = service.salvar(dto);
        return ApiResponse.success(response);
    }

    // @GetMapping("{id}")
    // @PreAuthorize("hasAnyRole('OPERADOR', 'GERENTE')")
    // public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(@PathVariable("id") String idEntrada){
    //     return service.obterPorId(UUID.fromString(idEntrada))
    //             .map(livro -> {
    //                 var dto = mapper.toDTO(livro);
    //                 return ResponseEntity.ok(dto);
    //             }).orElseGet(() -> ResponseEntity.notFound().build());
    // }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('OPERADOR', 'GERENTE')")
    public ResponseEntity<Object> deletarLivro(@PathVariable String id){
       return service.obterPorId(UUID.fromString(id))
                .map(livro -> {
                    service.deletar(livro);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

