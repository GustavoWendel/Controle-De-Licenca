package com.br.controledelicenca.service;

import com.br.controledelicenca.domain.Cliente;
import com.br.controledelicenca.repository.ClienteRepository;
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
public class ClienteService {

    private final ClienteRepository repository;

    @Transactional
    public Cliente salvarCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    public Page<Cliente> listarTodosClientes(Cliente filter, Pageable pageableRequest) {

        Example<Cliente> example = Example.of(filter, ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
        );

        return repository.findAll(example, pageableRequest);
    }

    public Optional<Cliente> buscaPorId(Long id) {
        return this.repository.findById(id);
    }

    public void deletarCliente(Cliente cliente) {
        if (cliente == null || cliente.getId() == null) {
            throw new IllegalArgumentException("Id de cliente não pode ser nulo");
        }
        this.repository.delete(cliente);
    }

    public Cliente atualizarCliente(Cliente cliente) {
        if (cliente == null || cliente.getId() == null) {
            throw new IllegalArgumentException("Id de cliente não pode ser nulo");
        }
        return this.repository.save(cliente);
    }
}
