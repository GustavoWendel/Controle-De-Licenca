package com.br.controledelicenca.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "table_licenca")
public class Licenca {
    @Id
    private Long id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataValidade;

    @OneToMany(mappedBy = "licenca", cascade = CascadeType.ALL)
    private Set<Produto> produtos;
}
