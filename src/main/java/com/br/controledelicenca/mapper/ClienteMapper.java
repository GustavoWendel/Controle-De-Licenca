package com.br.controledelicenca.mapper;

import com.br.controledelicenca.domain.Cliente;
import com.br.controledelicenca.request.ClientePostRequestBody;
import com.br.controledelicenca.request.ClientePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ClienteMapper {
    public static final ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    public abstract Cliente toCliente(ClientePostRequestBody ClientePostRequestBody);

    public abstract Cliente toCliente(ClientePutRequestBody ClientePutRequestBody);
}


