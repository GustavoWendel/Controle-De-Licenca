package com.br.controledelicenca.request;

import lombok.Data;

@Data
public class ClientePostRequestBody {
    private String nome;
    private String cnpj;
    private String email;
}
