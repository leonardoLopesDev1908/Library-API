package com.jpa.libraryapi.Controller.mappers;

import org.mapstruct.Mapper;

import com.jpa.libraryapi.Controller.dto.AutorDTO;
import com.jpa.libraryapi.model.Autor;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);
}
