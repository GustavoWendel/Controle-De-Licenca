package com.br.controledelicenca.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

    @NotNull
    private String nome;
    @NotNull
    private String cnpj;
    @NotNull
    private String email;
    @NotNull
    private String cep;

}
