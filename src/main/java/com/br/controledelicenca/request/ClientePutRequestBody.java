package com.br.controledelicenca.request;

import lombok.Data;

@Data
public class ClientePutRequestBody {
    private Long id;
    private String nome;
    private String cnpj;
    private String email;
}
