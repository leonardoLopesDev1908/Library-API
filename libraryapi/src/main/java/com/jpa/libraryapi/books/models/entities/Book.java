package com.jpa.libraryapi.books.models.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Book {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="isbn", length=20, nullable=false)
    private String isbn;

    @Column(name="title", length=100, nullable=false)
    private String title;

    @Column(name="publication_date")
    private LocalDate publicationDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name="genre", length=30, nullable=false)
    private BookGenre genre;

    @Column(name="price", precision=18, scale=2) 
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_author", nullable = false)
    private Author author;

    @CreatedDate
    @Column(name="registered_at")
    private LocalDateTime registeredAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    // @ManyToOne
    // @JoinColumn(name="id_usuario")
    // private UUID usuario;

}
