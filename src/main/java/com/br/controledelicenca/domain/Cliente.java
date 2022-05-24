package com.br.controledelicenca.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "table_cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = -3656431259068389491L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cliente", unique = true, nullable = false)
    private Long id;
    private String nome;
    private String cnpj;
    private String email;

    /*@OneToMany(cascade = CascadeType.ALL)
    private List<Produto> produtos;*/

    public Cliente() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(nome, cliente.nome) && Objects.equals(cnpj, cliente.cnpj) && Objects.equals(email, cliente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cnpj, email);
    }
}
