package com.vinicius.empacotamento.service;

import com.vinicius.empacotamento.domain.caixa.Caixa;
import com.vinicius.empacotamento.domain.caixa.CaixaRepository;
import com.vinicius.empacotamento.domain.pedido.Pedido;
import com.vinicius.empacotamento.domain.pedido.PedidoRepository;
import com.vinicius.empacotamento.domain.produto.Produto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmpacotamentoService {

    private final PedidoRepository pedidoRepository;
    private final CaixaRepository caixaRepository;

    public EmpacotamentoService(PedidoRepository pedidoRepository, CaixaRepository caixaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.caixaRepository = caixaRepository;
    }

    @Transactional
    public Pedido processarPedido(Pedido pedido) {
        List<Produto> produtos = new ArrayList<>(pedido.getProdutos());
        produtos.sort(Comparator.comparingDouble(p -> -p.getDimensao().getVolume()));

        List<Caixa> caixasDisponiveis = caixaRepository.findAll();
        caixasDisponiveis.sort(Comparator.comparingDouble(c -> c.getDimensao().getVolume()));

        List<Caixa> caixasUsadas = new ArrayList<>();

        for (Produto produto : produtos) {
            Caixa caixaSelecionada = encontrarCaixaQueComportaProduto(caixasUsadas, produto);

            if (caixaSelecionada != null) {
                caixaSelecionada.getProdutos().add(produto);
            } else {
                Optional<Caixa> novaCaixaOptional = caixasDisponiveis.stream()
                        .filter(c -> cabeDentro(produto, c))
                        .findFirst();

                if (novaCaixaOptional.isEmpty()) {
                    throw new RuntimeException("Nenhuma caixa disponível comporta o produto: " + produto.getNome());
                }

                Caixa novaCaixa = clonarCaixa(novaCaixaOptional.get());
                novaCaixa.setPedido(pedido);
                novaCaixa.setProdutos(new ArrayList<>(List.of(produto)));
                caixasUsadas.add(novaCaixa);
            }
        }

        pedido.setCaixas(caixasUsadas);
        return pedidoRepository.save(pedido);
    }

    private Caixa encontrarCaixaQueComportaProduto(List<Caixa> caixas, Produto produto) {
        for (Caixa caixa : caixas) {
            double volumeOcupado = caixa.getProdutos().stream()
                    .mapToDouble(p -> p.getDimensao().getVolume())
                    .sum();

            double volumeTotal = caixa.getDimensao().getVolume();

            if ((volumeOcupado + produto.getDimensao().getVolume()) <= volumeTotal) {
                return caixa;
            }
        }
        return null;
    }

    private boolean cabeDentro(Produto produto, Caixa caixa) {
        var dProd = produto.getDimensao();
        var dCaixa = caixa.getDimensao();
        return dProd.getAltura() <= dCaixa.getAltura()
                && dProd.getLargura() <= dCaixa.getLargura()
                && dProd.getComprimento() <= dCaixa.getComprimento();
    }

    private Caixa clonarCaixa(Caixa modelo) {
        Caixa nova = new Caixa();
        nova.setNome(modelo.getNome());
        nova.setDimensao(modelo.getDimensao());
        nova.setProdutos(new ArrayList<>());
        return nova;
    }
}
