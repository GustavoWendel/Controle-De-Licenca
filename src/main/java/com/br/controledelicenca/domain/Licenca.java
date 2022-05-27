package com.br.controledelicenca.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
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

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataInicio;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataValidade;

    @OneToMany(mappedBy = "licenca", cascade = CascadeType.ALL)
    private Set<Produto> produtos;

}
