package com.jpa.libraryapi.Controller.mappers;

import org.mapstruct.Mapper;

import com.jpa.libraryapi.Controller.dto.UsuarioDTO;
import com.jpa.libraryapi.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    
    Usuario toEntity(UsuarioDTO dto);
}
