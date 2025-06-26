package com.jpa.libraryapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpa.libraryapi.service.TransacaoService;


@SpringBootTest
public class TransacoesTest {
    
    @Autowired 
    TransacaoService transacao;

    /*
     * Commit -> confirmar alterações
     * Rollback -> desfazer alterações
     */
    @Test
    void transacaoSimples(){
        transacao.executar();
    }

    @Test
    void transacaoEstadoManaged(){
        transacao.atualizacaoSemAtualizar();
    }
}
