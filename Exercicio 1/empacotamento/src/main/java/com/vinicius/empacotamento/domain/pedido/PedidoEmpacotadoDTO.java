package com.vinicius.empacotamento.domain.pedido;

import com.vinicius.empacotamento.domain.caixa.CaixaEmpacotadaDTO;

import java.util.List;

public record PedidoEmpacotadoDTO(
        Long pedido_id,
        List<CaixaEmpacotadaDTO> caixas,
        int totalCaixasUtilizadas

) {
}
