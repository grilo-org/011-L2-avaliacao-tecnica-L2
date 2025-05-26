package com.vinicius.empacotamento.domain.produto;

import com.vinicius.empacotamento.domain.dimensoes.DadosDimencoes;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProduto(
        @NotBlank
        String nome,

        @NotNull @Valid
        DadosDimencoes dimencoes
) {
}
