package com.jpa.libraryapi.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.libraryapi.model.Autor;
import com.jpa.libraryapi.model.GeneroLivro;
import com.jpa.libraryapi.model.Livro;
import com.jpa.libraryapi.repository.AutorRepository;
import com.jpa.libraryapi.repository.LivroRepository;

@Service
public class TransacaoService {
    
    @Autowired
    private AutorRepository autorRepository;
    
    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void salvarLivroComFoto(){
        
    }

    @Transactional
    public void atualizacaoSemAtualizar(){
        var livro = livroRepository.findById(UUID.fromString("de72a436-d3e1-4e88-b67f-4e3c1b807203"))
                                                    .orElse(null);
        livro.setDataPublicacao(LocalDate.of(2024, 6, 1));
    }

    @Transactional
    public void executar(){
        
        Autor autor = new Autor();
		autor.setNome("José");
		autor.setNacionalidade("Brasileira");
		autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setAutor(autor);
        livro.setIsbn("90887-84875");
        livro.setPreco(BigDecimal.valueOf(80));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("José");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));
        
        livroRepository.save(livro);

        if(autor.getNome().equals("rollback")){
            throw new RuntimeException("Rollback");
        }
    }

}
