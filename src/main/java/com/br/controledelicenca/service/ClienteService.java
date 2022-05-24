package com.br.controledelicenca.service;

import com.br.controledelicenca.domain.Cliente;
import com.br.controledelicenca.mapper.ClienteMapper;
import com.br.controledelicenca.repository.ClienteRepository;
import com.br.controledelicenca.request.ClientePostRequestBody;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    @Transactional
    public Cliente salvarCliente(ClientePostRequestBody clientePostRequestBody) {
        ClienteMapper.INSTANCE.toCliente(clientePostRequestBody);
        return repository.save(ClienteMapper.INSTANCE.toCliente(clientePostRequestBody));
    }

    public

}
