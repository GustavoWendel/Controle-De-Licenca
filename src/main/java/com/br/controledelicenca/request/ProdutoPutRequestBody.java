package com.br.controledelicenca.request;

public class ProdutoPutRequestBody {

    private Long id;
    private String nome;

    public ProdutoPutRequestBody() {
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
}
