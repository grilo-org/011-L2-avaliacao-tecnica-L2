package com.vinicius.empacotamento.domain.pedido;

import com.vinicius.empacotamento.domain.caixa.Caixa;
import com.vinicius.empacotamento.domain.produto.DadosDetalhamentoProduto;

import java.util.List;

public record DadosDetalhamentoPedido(
        Long id,
        List<DadosDetalhamentoCaixaNoPedido> caixas
) {
    public DadosDetalhamentoPedido(Pedido pedido) {
        this(
                pedido.getId(),
                pedido.getCaixas().stream()
                        .map(DadosDetalhamentoCaixaNoPedido::new)
                        .toList()
        );
    }

    public record DadosDetalhamentoCaixaNoPedido(
            String nome,
            List<DadosDetalhamentoProduto> produtos
    ) {
        public DadosDetalhamentoCaixaNoPedido(Caixa caixa) {
            this(
                    caixa.getNome(),
                    caixa.getProdutos().stream()
                            .map(DadosDetalhamentoProduto::new)
                            .toList()
            );
        }
    }
}
