package com.vinicius.empacotamento.domain.pedido;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroPedido(
        @NotNull
        List<Long> produtosIds
) {
}
