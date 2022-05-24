package com.br.controledelicenca.service;

import com.br.controledelicenca.domain.Cliente;
import com.br.controledelicenca.exceptions.BadRequestException;
import com.br.controledelicenca.mapper.ClienteMapper;
import com.br.controledelicenca.repository.ClienteRepository;
import com.br.controledelicenca.request.ClientePostRequestBody;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    @Transactional
    public Cliente salvarCliente(ClientePostRequestBody clientePostRequestBody) {
        ClienteMapper.INSTANCE.toCliente(clientePostRequestBody);
        return repository.save(ClienteMapper.INSTANCE.toCliente(clientePostRequestBody));
    }

    public Page<Cliente> listarTodosClientes(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Cliente findByIdOrThrowBadRequestException(Long id) {
        Optional<Cliente> cliente = repository.findById(id);
        repository.findById(id).orElseThrow(() -> new BadRequestException("Cliente not found"));
        return cliente.orElseThrow();
    }

    public void deletarCliente(Long id) {
        repository.delete(findByIdOrThrowBadRequestException(id));
    }
}
