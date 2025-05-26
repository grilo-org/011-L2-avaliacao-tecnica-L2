package com.vinicius.empacotamento.domain.caixa;

import com.vinicius.empacotamento.domain.produto.ProdutoNaCaixaDTO;

import java.util.List;

public record CaixaEmpacotadaDTO(
        String codigoCaixa,
        double volumeUtilizado,
        double volumeTotal,
        List<ProdutoNaCaixaDTO> produtos
) {
}
