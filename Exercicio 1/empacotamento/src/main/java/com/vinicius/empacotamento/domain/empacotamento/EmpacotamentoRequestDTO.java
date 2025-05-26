package com.vinicius.empacotamento.domain.empacotamento;

import com.vinicius.empacotamento.domain.pedido.PedidoInputDTO;

import java.util.List;

public record EmpacotamentoRequestDTO(
        List<PedidoInputDTO> pedidos
) {
}
