package com.br.controledelicenca.api.resource;

import com.br.controledelicenca.domain.Produto;
import com.br.controledelicenca.request.ProdutoDto;
import com.br.controledelicenca.service.ProdutoService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
@Log4j2
public class ProdutoController {

    private final ModelMapper mapper;

    private final ProdutoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDto salvar(@RequestBody @Valid ProdutoDto dto) {
        Produto entity = mapper.map(dto, Produto.class);
        entity = service.salvarProduto(entity);
        return mapper.map(entity, ProdutoDto.class);
    }

    @GetMapping
    public Page<ProdutoDto> find(ProdutoDto dto, @Parameter(hidden = true) Pageable pageRequest) {
        Produto filter = mapper.map(dto, Produto.class);
        Page<Produto> result = service.listarTodosProdutos(filter, pageRequest);
        List<ProdutoDto> list = result.getContent()
                .stream()
                .map(entity -> mapper.map(entity, ProdutoDto.class))
                .collect(Collectors.toList());
        return new PageImpl<>(list, pageRequest, result.getTotalElements());
    }

    @GetMapping(path = "/{id}")
    public ProdutoDto findById(@PathVariable Long id) {
        return service
                .buscaPorId(id)
                .map(produto -> mapper.map(produto, ProdutoDto.class))
                .orElseThrow((() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        Produto produto = service.buscaPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        service.deletarProduto(produto);
    }

    @PutMapping("{id}")
    public ProdutoDto atualizar(@RequestBody @PathVariable Long id, @RequestBody @Valid ProdutoDto dto) {
        return service.buscaPorId(id).map(produto -> {
            produto.setNome(dto.getNome());
            service.atualizarProduto(produto);
            return mapper.map(produto, ProdutoDto.class);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
