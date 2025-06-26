package com.jpa.libraryapi.Controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.libraryapi.Controller.dto.AutorDTO;
import com.jpa.libraryapi.Controller.mappers.AutorMapper;
import com.jpa.libraryapi.model.Autor;
import com.jpa.libraryapi.service.AutorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController implements GenericController{
 
    @Autowired
    AutorService service;

    @Autowired 
    AutorMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid AutorDTO dto){
        Autor autor = mapper.toEntity(dto);
        service.salvar(autor);

        URI location = gerarHeaderLocation(autor.getId());

        return ResponseEntity.created(location).build();
    }


    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id){
        var idAutor = UUID.fromString(id);

        return service
                .obterPorId(idAutor)
                .map(autor -> {
                    AutorDTO dto = mapper.toDTO(autor);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable("id") String id){
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);

        if(autorOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        service.deletar(autorOptional.get());
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<AutorDTO>> pesquisar(
            @RequestParam(value = "nome", required=false) String nome,
            @RequestParam(value = "nacionalidade", required=false) String nacionalidade){
        List<Autor> autores = service.pesquisaByExample(nome, nacionalidade);
        List<AutorDTO> autoresDTO = autores
                                        .stream()
                                        .map(mapper::toDTO)
                                        .collect(Collectors.toList());
        
        return ResponseEntity.ok(autoresDTO);
    }
    

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") String id, 
                                            @RequestBody AutorDTO dto){
                                             
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);
        if(autorOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var autor = autorOptional.get();
        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());
        autor.setDataNascimento(dto.dataNascimento());

        service.atualizar(autor);

        return ResponseEntity.noContent().build();
    }
    
}
