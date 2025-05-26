package com.vinicius.empacotamento.domain.dimensoes;

import jakarta.validation.constraints.NotBlank;

public record DadosDimencoes(
        @NotBlank
        double altura,

        @NotBlank
        double largura,

        @NotBlank
        double comprimento
) {
}
