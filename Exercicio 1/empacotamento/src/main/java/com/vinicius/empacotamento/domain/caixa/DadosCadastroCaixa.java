package com.vinicius.empacotamento.domain.caixa;

import com.vinicius.empacotamento.domain.dimensoes.DadosDimencoes;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCaixa(
        @NotBlank
        String nome,

        @Valid @NotNull
        DadosDimencoes dimensoes
) {
}
