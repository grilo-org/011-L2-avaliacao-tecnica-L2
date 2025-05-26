package com.vinicius.empacotamento.service;

import com.vinicius.empacotamento.domain.caixa.Caixa;
import com.vinicius.empacotamento.domain.caixa.CaixaEmpacotadaDTO;
import com.vinicius.empacotamento.domain.empacotamento.EmpacotamentoRequestDTO;
import com.vinicius.empacotamento.domain.empacotamento.EmpacotamentoResponseDTO;
import com.vinicius.empacotamento.domain.pedido.PedidoEmpacotadoDTO;
import com.vinicius.empacotamento.domain.pedido.PedidoInputDTO;
import com.vinicius.empacotamento.domain.produto.ProdutoInputDTO;
import com.vinicius.empacotamento.domain.produto.ProdutoNaCaixaDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class EmpacotamentoService {

    public EmpacotamentoResponseDTO empacotarPedidos(EmpacotamentoRequestDTO request, List<Caixa> caixasDisponiveis) {
        List<PedidoEmpacotadoDTO> pedidosEmpacotados = request.pedidos().stream()
                .map(pedido -> empacotarPedido(pedido, caixasDisponiveis))
                .toList();

        return new EmpacotamentoResponseDTO(pedidosEmpacotados);
    }

    private PedidoEmpacotadoDTO empacotarPedido(PedidoInputDTO pedido, List<Caixa> caixasDisponiveis) {
        // Ordena caixas por volume (menor primeiro para otimização)
        List<Caixa> caixasOrdenadas = caixasDisponiveis.stream()
                .sorted(Comparator.comparingDouble(Caixa::getVolumeMaximo))
                .toList();

        List<ProdutoInputDTO> produtosParaEmpacotar = new ArrayList<>(pedido.produtos());
        List<CaixaEmpacotadaDTO> caixasUtilizadas = new ArrayList<>();

        while (!produtosParaEmpacotar.isEmpty()) {
            Optional<CaixaEmpacotadaDTO> caixaOpt = encontrarMelhorCaixa(produtosParaEmpacotar, caixasOrdenadas);

            if (caixaOpt.isPresent()) {
                CaixaEmpacotadaDTO caixa = caixaOpt.get();
                caixasUtilizadas.add(caixa);
                // Remove produtos já empacotados
                caixa.produtos().forEach(p ->
                        produtosParaEmpacotar.removeIf(prod -> prod.produto_id().equals(p.produto_id())));
            } else {
                // Caso algum produto não caiba em nenhuma caixa
                throw new RuntimeException(
                        String.format("Produto %s não cabe em nenhuma caixa disponível",
                                produtosParaEmpacotar.get(0).produto_id()));
            }
        }

        return new PedidoEmpacotadoDTO(
                pedido.pedido_id(),
                caixasUtilizadas,
                caixasUtilizadas.size()
        );
    }

    private Optional<CaixaEmpacotadaDTO> encontrarMelhorCaixa(
            List<ProdutoInputDTO> produtos, List<Caixa> caixas) {

        for (Caixa caixa : caixas) {
            List<ProdutoInputDTO> produtosNaCaixa = new ArrayList<>();
            double volumeOcupado = 0;

            for (ProdutoInputDTO produto : produtos) {
                if (cabeNaCaixa(produto, caixa)) {
                    double volumeProduto = produto.dimensoes().altura() *
                            produto.dimensoes().largura() *
                            produto.dimensoes().comprimento();
                    if (volumeOcupado + volumeProduto <= caixa.getVolumeMaximo()) {
                        produtosNaCaixa.add(produto);
                        volumeOcupado += volumeProduto;
                    }
                }
            }

            if (!produtosNaCaixa.isEmpty()) {
                List<ProdutoNaCaixaDTO> produtosDTO = produtosNaCaixa.stream()
                        .map(p -> new ProdutoNaCaixaDTO(p.produto_id()))
                        .toList();

                return Optional.of(new CaixaEmpacotadaDTO(
                        caixa.getCodigo(),
                        volumeOcupado,
                        caixa.getVolumeMaximo(),
                        produtosDTO
                ));
            }
        }

        return Optional.empty();
    }

    private boolean cabeNaCaixa(ProdutoInputDTO produto, Caixa caixa) {
        // Implementação simples - pode ser melhorada com rotação 3D
        return produto.dimensoes().altura() <= caixa.getAltura() &&
                produto.dimensoes().largura() <= caixa.getLargura() &&
                produto.dimensoes().comprimento() <= caixa.getComprimento();
    }
}