package com.br.controledelicenca.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto {

    @NotEmpty
    @Schema(description = "Este é o nome de produto", example = "Máquina de lavar roupa")
    private String nome;

}
