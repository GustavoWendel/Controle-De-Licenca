package com.br.controledelicenca.service;

import com.br.controledelicenca.domain.Produto;
import com.br.controledelicenca.exceptions.BadRequestException;
import com.br.controledelicenca.mapper.ProdutoMapper;
import com.br.controledelicenca.repository.ProdutoRepository;
import com.br.controledelicenca.request.ProdutoPostRequestBody;
import com.br.controledelicenca.request.ProdutoPutRequestBody;
import lombok.RequiredArgsConstructor;
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
    public Produto salvarProduto(ProdutoPostRequestBody produtoPostRequestBody) {
        ProdutoMapper.INSTANCE.toProduto(produtoPostRequestBody);
        return repository.save(ProdutoMapper.INSTANCE.toProduto(produtoPostRequestBody));
    }

    public Page<Produto> listarTodosProdutos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Produto findByIdOrThrowBadRequestException(Long id) {
        Optional<Produto> produto = repository.findById(id);
        repository.findById(id).orElseThrow(() -> new BadRequestException("Produto not found"));
        return produto.orElseThrow();
    }

    public void deletarProduto(Long id) {
        repository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void atualizarProduto(ProdutoPutRequestBody produtoPutRequestBody) {
        Produto produtoSalvo = findByIdOrThrowBadRequestException(produtoPutRequestBody.getId());
        Produto produto = ProdutoMapper.INSTANCE.toProduto(produtoPutRequestBody);
        produto.setId(produtoSalvo.getId());
        repository.save(produto);
    }
}
