package com.br.controledelicenca.api;

import com.br.controledelicenca.domain.Cliente;
import com.br.controledelicenca.request.ClientePostRequestBody;
import com.br.controledelicenca.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody ClientePostRequestBody clientePostRequestBody) {
        return new ResponseEntity<>(service.salvarCliente(clientePostRequestBody), HttpStatus.CREATED);
    }

}
