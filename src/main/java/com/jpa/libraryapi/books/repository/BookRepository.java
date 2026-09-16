package com.jpa.libraryapi.books.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.jpa.libraryapi.books.models.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jpa.libraryapi.books.models.entities.Book;
import com.jpa.libraryapi.books.models.entities.BookGenre;

import jakarta.transaction.Transactional;

/**
 * @see LivroRepositoryTest
 */

public interface BookRepository extends JpaRepository<Book, UUID>, JpaSpecificationExecutor<Book>{

    Page<Book> findByAuthor(Author author, Pageable pageable);

    //Query Method
    List<Book> findByAuthor(Author author);

    boolean existsByAuthor(Author author);
    
    List<Book> findByTitle(String titulo);

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByTitleAndPrice(String titulo, BigDecimal preco);

    List<Book> findByTitleLike(String titulo);

    // JPQL -> referencias às entidades e propriedades
    @Query("select l from Book as l "+
            "order by l.title, l.price")
    List<Book> listTodos();

    @Query("select a from Book l " + 
            "join l.author a")
    List<Author> listBooksAuthors();

    @Query("select l.title from Book l" +
           " order by l.title")
    List<String> listTitulos();

    @Query("""
        select l.genre from Book l
        join l.author a 
        where a.nationality = "brasileiro"
        order by l.genre 
    """)
    List<String> listGenresAuthorsBr();

    @Query("select l from Book l where l.genre = :nomeGenre")
    List<Book> findBygenre(@Param("nomeGenre") BookGenre nomeGenre);
            
    @Query("select l from Book l where l.genre = ?1 order by l.price")
    List<Book> findBygenreOrderByPreco(BookGenre genreBook);
    
    @Modifying
    @Transactional
    @Query("delete from Book where genre = ?1 ")
    void deleteByGenre(BookGenre genero);

    @Modifying
    @Transactional
    @Query("update Book set publicationDate = ?1 where genre = 'FICCAO'")
    void updatePublicationDate(LocalDate novaData);
} 