package com.br.controledelicenca.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ClientePostRequestBody {
    @NotEmpty(message = "O nome não pode estar vazio")
    @NotNull(message = "O nome não pode ser nulo")
    private String nome;
    @NotEmpty(message = "O Cnpj não pode estar vazio")
    @NotNull(message = "O Cnpj não pode ser nulo")
    private String cnpj;
    @NotEmpty(message = "O e-mail  não pode estar vazio")
    @NotNull(message = "O e-mail não pode ser nulo")
    private String email;
}
