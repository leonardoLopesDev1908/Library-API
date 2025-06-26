package com.jpa.libraryapi.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpa.libraryapi.model.Autor;
import com.jpa.libraryapi.model.GeneroLivro;
import com.jpa.libraryapi.model.Livro;

@SpringBootTest
public class AutorRepositoryTest {
    
    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
		autor.setNome("Maria");
		autor.setNacionalidade("brasileiro");
		autor.setDataNascimento(LocalDate.of(1951, 1, 31));

		var autorSalvo = autorRepository.save(autor);
		System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("37d048f7-aa17-4c90-b751-4ccf643067bd");

        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if(possivelAutor.isPresent()){
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor: ");
            System.out.println(possivelAutor.get());

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));

            autorRepository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest(){
        List<Autor> lista = autorRepository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + autorRepository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("37d048f7-aa17-4c90-b751-4ccf643067bd");
        autorRepository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("7b95b474-c8fa-49d1-a27c-af909fe4be48");
        var joao = autorRepository.findById(id).get();
        autorRepository.delete(joao);
    }

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Cristiano");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1970, 8, 5));

        Livro livro = new Livro();
        livro.setIsbn("90887-84876");
        livro.setPreco(BigDecimal.valueOf(120));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("Lula Volume 2");
        livro.setDataPublicacao(LocalDate.of(2000, 1, 2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("90887-84877");
        livro2.setPreco(BigDecimal.valueOf(50));
        livro2.setGenero(GeneroLivro.MISTERIO);
        livro2.setTitulo("Morte no nilo");
        livro2.setDataPublicacao(LocalDate.of(2000, 1, 2));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        autorRepository.save(autor);

        livroRepository.saveAll(autor.getLivros());
    }

    @Test 
    void listarLivrosAutor(){
        UUID id = UUID.fromString("ef6d7928-faed-4280-bff1-ed93c4d45a01");
        var autor = autorRepository.findById(id).get();
        
        List<Livro> lista = livroRepository.findByAutor(autor);
        autor.setLivros(lista);

        autor.getLivros().forEach(System.out::println);
    }
}
