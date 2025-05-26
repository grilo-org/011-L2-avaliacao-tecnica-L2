package com.vinicius.empacotamento.domain.pedido;

import com.vinicius.empacotamento.domain.produto.ProdutoRepository;
import com.vinicius.empacotamento.service.EmpacotamentoService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final EmpacotamentoService empacotamentoService;
    private final ProdutoRepository produtoRepository;

    public PedidoController(EmpacotamentoService empacotamentoService, ProdutoRepository produtoRepository) {
        this.empacotamentoService = empacotamentoService;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody @Valid DadosCadastroPedido dto) {
        List<Long> idsProdutos = dto.produtosIds();

        var produtos = produtoRepository.findAllById(idsProdutos);
        if (produtos.size() != idsProdutos.size()) {
            return ResponseEntity.badRequest().build();
        }

        Pedido pedido = new Pedido();
        pedido.setProdutos(produtos);

        Pedido pedidoEmpacotado = empacotamentoService.processarPedido(pedido);
        return ResponseEntity.ok(pedidoEmpacotado);
    }
}
