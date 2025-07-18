package com.jpa.libraryapi.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jpa.libraryapi.model.Autor;
import com.jpa.libraryapi.model.GeneroLivro;
import com.jpa.libraryapi.model.Livro;

import jakarta.transaction.Transactional;


/**
 * @see LivroRepositoryTest
 */

public interface LivroRepository extends JpaRepository<Livro, UUID>, JpaSpecificationExecutor<Livro>{

    Page<Livro> findByAutor(Autor autor, Pageable pageable); 

    List<Livro> findByAutor(Autor autor);

    boolean existsByAutor(Autor autor);
    
    List<Livro> findByTitulo(String titulo);

    Optional<Livro> findByIsbn(String isbn);

    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    List<Livro> findByTituloLike(String titulo);

    // JPQL -> referencias às entidades e propriedades
    @Query("select l from Livro as l "+
            "order by l.titulo, l.preco") 
    List<Livro> listarTodos();

    @Query("select a from Livro l " + 
            "join l.autor a")
    List<Autor> listarAutoresDosLivros();

    @Query("select l.titulo from Livro l" + 
           " order by l.titulo")
    List<String> listarTitulos();

    @Query("""
        select l.genero from Livro l
        join l.autor a 
        where a.nacionalidade = "brasileiro"
        order by l.genero 
    """)
    List<String> listarGenerosAutoresBr();

    @Query("select l from Livro l where l.genero = :nomeGenero")
    List<Livro> findByGenero(@Param("nomeGenero") GeneroLivro nomeGenero);
            
    @Query("select l from Livro l where l.genero = ?1 order by l.preco")
    List<Livro> findByGeneroOrderByPreco(GeneroLivro generoLivro);                  
    
    @Modifying
    @Transactional
    @Query("delete from Livro where genero = ?1 ")
    void deleteByGenero(GeneroLivro genero);

    @Modifying
    @Transactional
    @Query("update Livro set dataPublicacao = ?1 where genero = 'FICCAO'")
    void updateDataPublicacao(LocalDate novaData);
} 