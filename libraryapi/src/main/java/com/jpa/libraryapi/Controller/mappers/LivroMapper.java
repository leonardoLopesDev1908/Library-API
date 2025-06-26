package com.jpa.libraryapi.Controller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.jpa.libraryapi.Controller.dto.CadastroLivroDTO;
import com.jpa.libraryapi.Controller.dto.ResultadoPesquisaLivroDTO;
import com.jpa.libraryapi.model.Livro;
import com.jpa.libraryapi.repository.AutorRepository;

@Mapper(componentModel = "spring", uses = AutorMapper.class)
public abstract class LivroMapper {
    
    @Autowired 
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java(autorRepository.findById(dto.idAutor()).orElse(null))")
    public abstract Livro toEntity(CadastroLivroDTO dto);

    public abstract ResultadoPesquisaLivroDTO toDTO(Livro livro);

}
