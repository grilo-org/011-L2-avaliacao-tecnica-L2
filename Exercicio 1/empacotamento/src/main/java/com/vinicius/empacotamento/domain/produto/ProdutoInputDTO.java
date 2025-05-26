package com.vinicius.empacotamento.domain.produto;

import com.vinicius.empacotamento.domain.DimensoesDTO;

public record ProdutoInputDTO(
        String produto_id,
        DimensoesDTO dimensoes
) {
}
