package com.br.controledelicenca.service;

import com.br.controledelicenca.domain.Produto;
import com.br.controledelicenca.repository.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ProdutoServiceTest {

    private ProdutoService service;

    @MockBean
    private ProdutoRepository repository;

    @BeforeEach
    public void setUp(){
        this.service = new ProdutoService(repository);
    }

    @Test
    @DisplayName("Deve salvar um Produto")
    public void salvarProdutoTest(){
        // Cenário
        Produto produto = criarProdutoValido();
        when(repository.save(produto)).thenReturn(
                (Produto.builder().id(1L)
                        .nome("Máquina de lavar roupa")
                        .build()));


        //Execução
        Produto ProdutoSalvo = service.salvarProduto(produto);

        //Verificação
        assertThat(ProdutoSalvo.getId()).isNotNull();
        assertThat(ProdutoSalvo.getNome()).isEqualTo("Máquina de lavar roupa");
    }

    @Test
    @DisplayName("Deve obter um Produto por id")
    public void buscaPorIdTest(){
        //Cenário
        Long id = 1l;
        Produto Produto = criarProdutoValido();
        Produto.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(Produto));

        //Execução
        Optional<Produto> ProdutoEncontrado = service.buscaPorId(id);

        //Verificações
        assertThat(ProdutoEncontrado.isPresent()).isTrue();
        assertThat(ProdutoEncontrado.get().getId()).isEqualTo(id);
        assertThat(ProdutoEncontrado.get().getNome()).isEqualTo(Produto.getNome());
    }

    @Test
    @DisplayName("Deve retornar vazio ao obter um Produto por id, quando ele não existe na base.")
    public void ProdutoNotFoundByIdTest(){
        //Cenário
        Long id = 1l;

        when(repository.findById(id)).thenReturn(Optional.empty());

        //Execução
        Optional<Produto> ProdutoEncontrado = service.buscaPorId(id);

        //Verificações
        assertThat(ProdutoEncontrado.isPresent()).isFalse();
    }


    @Test
    @DisplayName("Deve deletar um Produto")
    public void deletarProdutoTest() {
        //Cenário
        Produto produto = Produto.builder()
                .id(1L)
                .nome("Wendel").build();

        //Execução
        service.deletarProduto(produto);

        //Verificações
        verify(repository, times(1)).delete(produto);
    }

    @Test
    @DisplayName("Deve ocorrer erro ao tentar deletar um Produto inexistente.")
    public void deletarProdutoInvalidoTest() {
        //Cenário
        Produto Produto = new Produto();
        //Execução
        Assertions.assertThrows(IllegalArgumentException.class, () ->  service.deletarProduto(Produto));
        //Vericação
        verify(repository, never()).delete(Produto);
    }

    @Test
    @DisplayName("Deve atualizar um Produto ")
    public void atualizarProdutoTest() {
        //Cenário
        long id = 1L;

        //Produto a atualizar
        Produto atualizandoProduto = Produto.builder().id(id).build();

        //Simulação
        Produto produtoAtualizado = criarProdutoValido();
        produtoAtualizado.setId(id);

        when(repository.save(atualizandoProduto)).thenReturn(produtoAtualizado);

        //Execução
        Produto produto = service.atualizarProduto(atualizandoProduto);

        //Verificações
        assertThat(produto.getId()).isEqualTo(id);
        assertThat(produto.getNome()).isEqualTo(produtoAtualizado.getNome());

    }

    @Test
    @DisplayName("Deve ocorrer erro ao tentar atualizar um Produto inexistente.")
    public void atualizarProdutoInvalidoTest() {
        //Cenário
        Produto Produto = new Produto();
        //Execução
        Assertions.assertThrows(IllegalArgumentException.class, () ->  service.atualizarProduto(Produto));
        //Vericação
        verify(repository, never()).save(Produto);
    }

    @Test
    @DisplayName("Deve filtrar os Produtos pelas propriedades")
    public void buscarProdutoTest() {
        //Cenário
        Produto Produto = criarProdutoValido();

        Pageable pageRequest = PageRequest.of(0, 10);

        List<Produto> lista = Collections.singletonList(Produto);

        Page<Produto> page = new PageImpl<>(lista, pageRequest, 1);
        when( repository.findAll(any(Example.class), any(PageRequest.class)) )
                .thenReturn(page);

        //Execução
        Page<Produto> result = service.listarTodosProdutos(Produto, pageRequest);

        //Verificações
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent()).isEqualTo(lista);
        assertThat(result.getPageable().getPageNumber()).isZero();
        assertThat(result.getPageable().getPageSize()).isEqualTo(10);
    }

    private Produto criarProdutoValido() {
        return Produto.builder()
                .nome("Máquina de lavar roupa")
                .build();
    }
}
