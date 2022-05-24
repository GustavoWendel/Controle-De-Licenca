package com.br.controledelicenca.api.resource;

import com.br.controledelicenca.domain.Cliente;
import com.br.controledelicenca.request.ClientePostRequestBody;
import com.br.controledelicenca.request.ClientePutRequestBody;
import com.br.controledelicenca.service.ClienteService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/cliente")
@RequiredArgsConstructor
@Log4j2
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody @Valid ClientePostRequestBody clientePostRequestBody) {
        return new ResponseEntity<>(service.salvarCliente(clientePostRequestBody), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Cliente>> listar(Pageable pageable){
        return ResponseEntity.ok(service.listarTodosClientes(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findByIdOrThrowBadRequestException(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletarCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> atualizar(@RequestBody ClientePutRequestBody clientePutRequestBody) {
        service.atualizarCliente(clientePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
