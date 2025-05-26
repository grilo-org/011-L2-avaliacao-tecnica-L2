package com.vinicius.empacotamento.domain.produto;

public record DadosDetalhamentoProduto(String nome) {
    public DadosDetalhamentoProduto(Produto produto) {
        this(produto.getNome());
    }
}
