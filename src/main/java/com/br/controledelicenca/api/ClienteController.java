package com.br.controledelicenca.api;

import com.br.controledelicenca.domain.Cliente;
import com.br.controledelicenca.request.ClientePostRequestBody;
import com.br.controledelicenca.service.ClienteService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cliente")
@RequiredArgsConstructor
@Log4j2
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody ClientePostRequestBody clientePostRequestBody) {
        return new ResponseEntity<>(service.salvarCliente(clientePostRequestBody), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Cliente>> listar(Pageable pageable){
        return ResponseEntity.ok(service.listarTodos(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findByIdOrThrowBadRequestException(id));
    }
}
