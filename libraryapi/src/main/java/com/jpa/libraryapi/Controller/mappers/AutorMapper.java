package com.jpa.libraryapi.Controller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jpa.libraryapi.Controller.dto.AutorDTO;
import com.jpa.libraryapi.model.Autor;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    @Mapping(target="dataAtualizacao", ignore=true)
    @Mapping(target="usuario", ignore=true)
    @Mapping(target="dataCadastro", ignore=true)
    @Mapping(target="livros", ignore=true)
    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);
}
