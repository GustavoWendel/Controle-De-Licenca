package com.br.controledelicenca.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

    private Long id;

    @NotNull
    @Schema(description = "Este é o nome do cliente", example = "Thiago Garcia")
    private String nome;
    @NotNull
    @Schema(description = "Este é o cnpj do cliente", example = "54.617.454/0001-35\t")
    private String cnpj;
    @NotNull
    @Schema(description = "Este é o e-mail do cliente", example = "teste@gmai.com")
    private String email;

}
