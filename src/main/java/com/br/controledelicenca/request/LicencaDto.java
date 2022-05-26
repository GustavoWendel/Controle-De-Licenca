package com.br.controledelicenca.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LicencaDto {

    private Long id;

    @NotNull
    @NotEmpty
    private Date dataInicio;
    @NotNull
    @NotEmpty
    private Date dataValidade;
}
