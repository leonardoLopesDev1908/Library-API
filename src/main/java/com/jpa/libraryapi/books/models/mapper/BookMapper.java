package com.jpa.libraryapi.books.models.mapper;

import com.jpa.libraryapi.books.models.dtos.request.CreateBookRequest;
import com.jpa.libraryapi.books.models.dtos.response.BookResponse;
import com.jpa.libraryapi.books.models.entities.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    //
//    public Book toEntity(CreateBookRequest request) {
//        return Book.builder()
//            .isbn(request.isbn())
//            .title(request.title())
//            .genre(request.genre())
//            .price(request.price())
//            .publicationDate(request.publicationDate())
//            .autorId(request.autorId())
//            .build();
//    }

    public BookResponse toDTO(Book book) {
        return new BookResponse(
            book.getIsbn(), book.getTitle(), 
            book.getGenre(), book.getPublicationDate(), 
            book.getPrice(), book.getRegisteredAt(), 
            book.getUpdatedAt()
        );
    }
}
