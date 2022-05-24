package com.br.controledelicenca.request;

public class ClientePutRequestBody {
    private Long id;
    private String nome;
    private String cnpj;
    private String email;

    public ClientePutRequestBody(Long id, String nome, String cnpj, String email) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
