package com.jpa.libraryapi.books.service;

import java.util.Optional;
import java.util.UUID;

import com.jpa.libraryapi.books.models.entities.Author;
import org.springframework.stereotype.Service;

import com.jpa.libraryapi.books.models.dtos.request.CreateBookRequest;
import com.jpa.libraryapi.books.models.dtos.response.BookResponse;
import com.jpa.libraryapi.books.models.entities.Book;
import com.jpa.libraryapi.books.models.mapper.BookMapper;
import com.jpa.libraryapi.books.repository.BookRepository;
import com.jpa.libraryapi.security.SecurityService;
//métodos estático de LivroSpecs

@Service
public class BookService {
    
    private final BookRepository repository; 
    private final SecurityService securityService;
    private final AuthorService authorService;
    private final BookMapper mapper;

    public BookService(BookRepository repository, SecurityService securityService,
                       AuthorService authorService, BookMapper mapper) {
        this.repository = repository;
        this.securityService = securityService;
        this.authorService = authorService;
        this.mapper = mapper;
    }

    public BookResponse salvar(CreateBookRequest request){
        Author author = authorService.obterPorId(request.autorId());
        Book book = Book.builder()
                .isbn(request.isbn())
                .title(request.title())
                .genre(request.genre())
                .author(author)
                .publicationDate(request.publicationDate())
                .build();

        return mapper.toDTO(repository.save(book));
    }

    public Optional<Book> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void deletar(Book livro){
        repository.delete(livro);
    }

    public void atualizar(Book livro){
        if(livro.getId()==null){
            throw new IllegalArgumentException("Esse livro ainda não está cadastrado.");
        }
        repository.save(livro);
    }
}
