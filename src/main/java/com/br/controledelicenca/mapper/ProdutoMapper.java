package com.br.controledelicenca.mapper;

import com.br.controledelicenca.domain.Produto;
import com.br.controledelicenca.request.ProdutoPostRequestBody;
import com.br.controledelicenca.request.ProdutoPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ProdutoMapper {
    public static final ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    public abstract Produto toProduto(ProdutoPostRequestBody produtoPostRequestBody);

    public abstract Produto toProduto(ProdutoPutRequestBody produtoPutRequestBody);
}
