package com.br.controledelicenca.api.resource;

import com.br.controledelicenca.domain.Cliente;
import com.br.controledelicenca.request.ClienteDto;
import com.br.controledelicenca.service.ClienteService;
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
@RequestMapping("api/clientes")
@RequiredArgsConstructor
@Log4j2
public class ClienteController {

    private final ModelMapper mapper;

    private final ClienteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto salvar(@RequestBody @Valid ClienteDto dto) {
        Cliente entity = mapper.map(dto, Cliente.class);
        entity = service.salvarCliente(entity);
        return mapper.map(entity, ClienteDto.class);
    }

    @GetMapping
    public Page<ClienteDto> find(ClienteDto dto, @Parameter(hidden = true) Pageable pageRequest) {
        Cliente filter = mapper.map(dto, Cliente.class);
        Page<Cliente> result = service.listarTodosClientes(filter, pageRequest);
        List<ClienteDto> list = result.getContent()
                .stream()
                .map(entity -> mapper.map(entity, ClienteDto.class))
                .collect(Collectors.toList());
        return new PageImpl<>(list, pageRequest, result.getTotalElements());
    }

    @GetMapping(path = "/{id}")
    public ClienteDto findById(@PathVariable Long id) {
        return service
                .buscaPorId(id)
                .map(cliente -> mapper.map(cliente, ClienteDto.class))
                .orElseThrow((() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        Cliente cliente = service.buscaPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        service.deletarCliente(cliente);
    }

    @PutMapping("/{id}")
    public ClienteDto atualizar(@RequestBody @PathVariable Long id, @RequestBody @Valid ClienteDto dto) {
        return service.buscaPorId(id).map(cliente -> {
            cliente.setNome(dto.getNome());
            cliente.setCnpj(dto.getCnpj());
            cliente.setEmail(dto.getEmail());
            service.atualizarCliente(cliente);
            return mapper.map(cliente, ClienteDto.class);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
