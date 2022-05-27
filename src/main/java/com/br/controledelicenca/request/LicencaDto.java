package com.br.controledelicenca.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LicencaDto {

    private Long id;

    @NotNull
    @NotEmpty
    @Schema(description = "Data de início da licença", example = "25/05/2022")
    private Date dataInicio;
    @NotNull
    @NotEmpty
    @Schema(description = "Data de validade da licença", example = "25/05/2022")
    private Date dataValidade;
}
