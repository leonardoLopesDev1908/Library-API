package com.jpa.libraryapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.libraryapi.Controller.dto.UsuarioDTO;
import com.jpa.libraryapi.Controller.mappers.UsuarioMapper;
import com.jpa.libraryapi.model.Usuario;
import com.jpa.libraryapi.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    UsuarioService service;
    
    @Autowired
    UsuarioMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody UsuarioDTO dto){
        Usuario user = mapper.toEntity(dto);
        service.salvar(user);
    }
}
