package com.jpa.libraryapi.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
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
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired 
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));
        
        Autor autor = autorRepository
                .findById(UUID.fromString("0a2c1921-79b6-4dc7-95e2-73d7a1f5aeee"))
                .orElse(null);

        livro.setAutor(autor);
        repository.save(livro);
    }

    //Salvar em cascata -> ambos juntos
    @Test
    void salvarCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("90887-84875");
        livro.setPreco(BigDecimal.valueOf(80));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Lord of Things");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));
        
        Autor autor = new Autor();
		autor.setNome("João");
		autor.setNacionalidade("brasileiro");
		autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        livro.setAutor(autor);
        repository.save(livro);
    }

    @Test
    void deletePorIdTest(){
        var id = UUID.fromString("5b396f7a-ca07-4994-b8a4-0423a852a459");
        repository.deleteById(id);
    }

    @Test
    void atualizarAutorLivro(){
        UUID id = UUID.fromString("6890b7b0-5428-4c0e-9681-8f91f630267e");
        var livroParaAtualizar = repository
                                .findById(id)
                                .orElse(null);

        UUID idAutor = UUID.fromString("ef6d7928-faed-4280-bff1-ed93c4d45a01");
        Autor autor = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(autor);

        repository.save(livroParaAtualizar);
    }

    @Test
    void buscarLivroTest(){
        UUID id = UUID.fromString("016b0f7b-80f4-4984-b692-e7b1f855ab50");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro:");
        System.out.println(livro.getTitulo());
        System.out.println("Autor:");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquisaPorTituloTest(){
        List<Livro> lista = repository.findByTitulo("Lord");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorIsbnTest(){
        Optional<Livro> livro = repository.findByIsbn("90887-84874");
        livro.ifPresent(System.out::println);
    }

    @Test
    void pesquisarPorTituloEPrecoTest(){
        var preco = BigDecimal.valueOf(80.00);
        var titulo = "Lord of Things";

        List<Livro> lista = repository.findByTituloAndPreco(titulo, preco);
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorTituloLike(){
        var titulo = "%Lord%";

        List<Livro> lista = repository.findByTituloLike(titulo);
        lista.forEach(System.out::println);
    }

    @Test
    void listarTodosLivros(){
        var resultado = repository.listarTodos();
        resultado.forEach(System.out::println);
    }
    
    @Test
    void listarAutores(){
        var resultado = repository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }
    
    @Test
    void listaDeTitulos(){
        var resultado = repository.listarTitulos();
        resultado.forEach(System.out::println);
    }
    
    @Test
    void listarGeneros(){
        var resultado = repository.listarGenerosAutoresBr();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQParam(){
        var resultado = repository.findByGenero(GeneroLivro.FICCAO);
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroPosicional(){
        var resultado = repository.findByGeneroOrderByPreco(GeneroLivro.FICCAO);
        resultado.forEach(System.out::println);
    }

    @Test
    void deleteTest(){
        repository.deleteByGenero(GeneroLivro.BIOGRAFIA);
    }

    @Test
    void updateTest(){
        repository.updateDataPublicacao(LocalDate.of(1999, 1, 1));
    }
}