package com.br.controledelicenca.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LicencaDto {
    @NotNull
    @NotEmpty
    private LocalDateTime dataInicio;
    @NotNull
    @NotEmpty
    private LocalDateTime dataValidade;
}
