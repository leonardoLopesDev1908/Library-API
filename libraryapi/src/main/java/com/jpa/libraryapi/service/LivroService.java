package com.jpa.libraryapi.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.jpa.libraryapi.model.GeneroLivro;
import com.jpa.libraryapi.model.Livro;
import com.jpa.libraryapi.model.Usuario;
import com.jpa.libraryapi.repository.LivroRepository;
import static com.jpa.libraryapi.repository.LivroSpecs.anoPublicacaoEqual;
import static com.jpa.libraryapi.repository.LivroSpecs.generoEqual;
import static com.jpa.libraryapi.repository.LivroSpecs.isbnEqual;
import static com.jpa.libraryapi.repository.LivroSpecs.nomeAutorLike; //importa todos os
import static com.jpa.libraryapi.repository.LivroSpecs.tituloLike;
import com.jpa.libraryapi.security.SecurityService;
import com.jpa.libraryapi.validator.LivroValidator;

import lombok.RequiredArgsConstructor;
//métodos estático de LivroSpecs

@Service
@RequiredArgsConstructor
public class LivroService {
    
    //outra forma de injeção de dependencias
    private final LivroRepository repository; 
    private final LivroValidator validator;
    private final SecurityService securityService;

    public Livro salvar(Livro livro){
        validator.validar(livro);
        Usuario usuario = securityService.obterUsuarioLogado();
        livro.setUsuario(usuario);
        return repository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void deletar(Livro livro){
        repository.delete(livro);
    }

    //Pesquisa com uso de specification
    public Page<Livro> pesquisa(
                String isbn, String titulo, String nomeAutor, GeneroLivro genero, Integer anoPublicacao,
                Integer pagina, Integer tamanhoPagina){
        
        Specification<Livro> specs = null;

        if(isbn != null && !isbn.isEmpty()){
            specs = (specs == null) ? isbnEqual(isbn) : specs.and(isbnEqual(isbn));
        }
        if(titulo != null && !titulo.isEmpty()){
            specs = (specs == null) ? tituloLike(titulo) : specs.and(tituloLike(titulo));
        }
        if(genero != null){
            specs = (specs == null) ? generoEqual(genero) : specs.and(generoEqual(genero));
        }
        if(anoPublicacao != null){
            specs = (specs == null) ? anoPublicacaoEqual(anoPublicacao) : specs.and(anoPublicacaoEqual(anoPublicacao));
        }
        if(nomeAutor != null){
            specs = (specs == null) ? nomeAutorLike(nomeAutor) : specs.and(nomeAutorLike(nomeAutor));
        }

        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);

        return repository.findAll(specs, pageRequest);
    }

    public void atualizar(Livro livro){
        if(livro.getId()==null){
            throw new IllegalArgumentException("Esse livro ainda não está cadastrado.");
        }
        validator.validar(livro);
        repository.save(livro);
    }
}
