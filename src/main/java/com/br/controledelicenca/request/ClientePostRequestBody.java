package com.br.controledelicenca.request;

import javax.validation.constraints.NotNull;

public class ClientePostRequestBody {

    @NotNull
    private String nome;
    @NotNull
    private String cnpj;
    @NotNull
    private String email;

    public ClientePostRequestBody(String nome, String cnpj, String email) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
