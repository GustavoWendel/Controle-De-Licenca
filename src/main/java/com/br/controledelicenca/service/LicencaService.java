package com.br.controledelicenca.service;

import com.br.controledelicenca.domain.Licenca;
import com.br.controledelicenca.repository.LicencaRepository;
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
public class LicencaService {

    private final LicencaRepository repository;

    @Transactional
    public Licenca salvarLicenca(Licenca licenca) {
        return repository.save(licenca);
    }

    public Page<Licenca> listarTodasLicencas(Licenca filter, Pageable pageableRequest) {

        Example<Licenca> example = Example.of(filter, ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
        );

        return repository.findAll(example, pageableRequest);
    }

    public Optional<Licenca> buscaPorId(Long id) {
        return this.repository.findById(id);
    }

    public void deletarLicenca(Licenca licenca) {
        if (licenca == null || licenca.getId() == null) {
            throw new IllegalArgumentException("Id de Licenca não pode ser nulo");
        }
        this.repository.delete(licenca);
    }

    public Licenca atualizarLicenca(Licenca licenca) {
        if (licenca == null || licenca.getId() == null) {
            throw new IllegalArgumentException("Id de Licenca não pode ser nulo");
        }
        return this.repository.save(licenca);
    }
}
