package com.jpa.libraryapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.jpa.libraryapi.exceptions.OperacaoNaoPermitidaException;
import com.jpa.libraryapi.model.Autor;
import com.jpa.libraryapi.model.Usuario;
import com.jpa.libraryapi.repository.AutorRepository;
import com.jpa.libraryapi.repository.LivroRepository;
import com.jpa.libraryapi.security.SecurityService;
import com.jpa.libraryapi.validator.AutorValidator;


@Service
//@RequiredArgsConstructor -> gera um construtor com as dependencias usadas
public class AutorService {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    AutorValidator validator;

    @Autowired 
    LivroRepository livroRepository;

    @Autowired
    SecurityService securityService;
    

    public Autor salvar(Autor autor){
        validator.validar(autor);
        Usuario usuario = securityService.obterUsuarioLogado();
        autor.setUsuario(usuario);

        return this.autorRepository.save(autor);
    }

    public void atualizar(Autor autor){
        validator.validar(autor);
        if(autor.getId()==null){
            throw new IllegalArgumentException("Nenhum autor com esse Id foi encontrado");
        }
        this.autorRepository.save(autor);
    }


    public Optional<Autor> obterPorId(UUID id){
        return autorRepository.findById(id);
    }

    public void deletar(Autor autor){
        if(possuiLivro(autor)){
            throw new OperacaoNaoPermitidaException("Sem permissão. Autor possui livros cadastrados!");
        }
        autorRepository.delete(autor);
    }

    public List<Autor> pesquisa(String nome, String nacionalidade){
        if(nome != null && nacionalidade != null){
            return autorRepository.findByNomeAndNacionalidade(nome, nacionalidade);
        } else if (nome != null){
            return autorRepository.findByNome(nome);
        } else if (nacionalidade != null){
            return autorRepository.findByNacionalidade(nacionalidade);
        }
        return autorRepository.findAll();
    }

    public List<Autor> pesquisaByExample(String nome, String nacionalidade){
        var autor = new Autor();
        autor.setNome(nome);
        autor.setNacionalidade(nacionalidade);
        
        ExampleMatcher matcher = ExampleMatcher
                    .matching()
                    .withIgnorePaths("id", "data_cadastro")
                    .withIgnoreNullValues()
                    .withIgnoreCase()
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Autor> autorExample = Example.of(autor, matcher);
        return autorRepository.findAll(autorExample);    
    }

    private boolean possuiLivro(Autor autor){
        return livroRepository.existsByAutor(autor);
    }
}
