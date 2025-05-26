package com.vinicius.empacotamento.domain.pedido;

import com.vinicius.empacotamento.domain.produto.ProdutoInputDTO;

import java.util.List;

public record PedidoInputDTO(
        Long pedido_id,
        List<ProdutoInputDTO> produtos
) {
}
