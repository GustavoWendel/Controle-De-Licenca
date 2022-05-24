package com.br.controledelicenca.api.resource;

import com.br.controledelicenca.domain.Produto;
import com.br.controledelicenca.request.ProdutoPostRequestBody;
import com.br.controledelicenca.request.ProdutoPutRequestBody;
import com.br.controledelicenca.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/produto")
@RequiredArgsConstructor
@Log4j2
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody ProdutoPostRequestBody produtoPostRequestBody) {
        return new ResponseEntity<>(produtoService.salvarProduto(produtoPostRequestBody), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Produto>> listar(Pageable pageable){
        return ResponseEntity.ok(produtoService.listarTodosProdutos(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id){
        return ResponseEntity.ok(produtoService.findByIdOrThrowBadRequestException(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        produtoService.deletarProduto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> atualizar(@RequestBody ProdutoPutRequestBody produtoPutRequestBody) {
        produtoService.atualizarProduto(produtoPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
