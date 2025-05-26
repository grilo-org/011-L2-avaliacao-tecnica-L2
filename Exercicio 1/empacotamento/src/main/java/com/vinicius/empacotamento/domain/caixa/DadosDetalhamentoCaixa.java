package com.vinicius.empacotamento.domain.caixa;

import com.vinicius.empacotamento.domain.produto.Produto;
import com.vinicius.empacotamento.domain.produto.DadosDetalhamentoProduto;

import java.util.List;

public record DadosDetalhamentoCaixa(
        String nome,
        List<DadosDetalhamentoProduto> produtos
) {
    public DadosDetalhamentoCaixa(Caixa caixa) {
        this(
                caixa.getNome(),
                caixa.getProdutos().stream()
                        .map(DadosDetalhamentoProduto::new)
                        .toList()
        );
    }
}
