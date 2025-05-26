package com.vinicius.empacotamento.domain.pedido;

import com.vinicius.empacotamento.domain.caixa.CaixaComProdutosDTO;

import java.util.List;

public record DetalhamentoPedidoDTO(
        Long pedido_id,
        List<CaixaComProdutosDTO> caixas
) {
}
