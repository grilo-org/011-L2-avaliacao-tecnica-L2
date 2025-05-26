package com.vinicius.empacotamento.domain.caixa;

import java.util.List;

public record CaixaComProdutosDTO(
        String caixa_id,
        List<String> produtos,
        String observacao
) {
}
