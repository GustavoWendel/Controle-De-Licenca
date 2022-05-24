package com.br.controledelicenca.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name= "table_produto")
public class Produto implements Serializable {
    private static final long serialVersionUID = -3656431259068389491L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_produto", unique = true, nullable = false)
    private Long id;
    private String nome;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Produto> produtos;

}
