package com.br.controledelicenca.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "table_produto")
public class Produto implements Serializable {
    private static final long serialVersionUID = -3656431259068389491L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_produto", unique = true, nullable = false)
    private Long id;
    private String nome;

    @JoinColumn(name = "id_licenca")
    @ManyToOne
    private Licenca licenca;
}
