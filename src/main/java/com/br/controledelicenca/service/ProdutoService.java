package com.br.controledelicenca.service;

import com.br.controledelicenca.domain.Produto;
import com.br.controledelicenca.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    
    private final ProdutoRepository repository;

    @Transactional
    public Produto salvarProduto(Produto Produto) {
        return repository.save(Produto);
    }

    public Page<Produto> listarTodosProdutos(Produto filter, Pageable pageableRequest) {

        Example<Produto> example = Example.of(filter, ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
        );

        return repository.findAll(example, pageableRequest);
    }

    public Optional<Produto> buscaPorId(Long id) {
        return this.repository.findById(id);
    }

    public void deletarProduto(Produto produto) {
        if (produto == null || produto.getId() == null) {
            throw new IllegalArgumentException("Id de Produto não pode ser nulo");
        }
        this.repository.delete(produto);
    }

    public Produto atualizarProduto(Produto produto) {
        if (produto == null || produto.getId() == null) {
            throw new IllegalArgumentException("Id de Produto não pode ser nulo");
        }
        return this.repository.save(produto);
    }
}
