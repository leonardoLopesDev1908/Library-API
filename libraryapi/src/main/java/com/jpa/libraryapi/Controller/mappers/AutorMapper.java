package com.jpa.libraryapi.Controller.mappers;

import org.mapstruct.Mapper;

import com.jpa.libraryapi.Controller.dto.AutorDTO;
import com.jpa.libraryapi.model.Autor;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    //@Mapping(source = "nome", target="nomeAutor") uso para campos que tenham nomes diferentes
    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);
}
