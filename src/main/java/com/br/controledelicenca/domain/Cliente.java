package com.br.controledelicenca.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    private String cep;

    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Produto> produtos;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Licenca> licencas;
}
