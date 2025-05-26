package com.vinicius.empacotamento.domain.empacotamento;

import com.vinicius.empacotamento.domain.pedido.PedidoEmpacotadoDTO;

import java.util.List;

public record EmpacotamentoResponseDTO(
        List<PedidoEmpacotadoDTO> pedidos
) {
}
