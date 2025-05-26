package com.vinicius.empacotamento.service;

import com.vinicius.empacotamento.domain.DimensoesDTO;
import com.vinicius.empacotamento.domain.caixa.Caixa;
import com.vinicius.empacotamento.domain.empacotamento.EmpacotamentoRequestDTO;
import com.vinicius.empacotamento.domain.empacotamento.EmpacotamentoResponseDTO;
import com.vinicius.empacotamento.domain.pedido.PedidoEmpacotadoDTO;
import com.vinicius.empacotamento.domain.pedido.PedidoInputDTO;
import com.vinicius.empacotamento.domain.produto.ProdutoInputDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmpacotamentoServiceTest {

    @InjectMocks
    private EmpacotamentoService empacotamentoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarPedidosEmpacotadosCorretamente() {
        // Arrange
        List<Caixa> caixasDisponiveis = List.of(
                new Caixa("CAIXA_P", 10.00, 10.00, 10.00),
                new Caixa("CAIXA_M", 20.00, 20.00, 20.00),
                new Caixa("CAIXA_G", 30.00, 30.00, 40.00)
        );

        EmpacotamentoRequestDTO request = new EmpacotamentoRequestDTO(
                List.of(
                        new PedidoInputDTO(1L, List.of(
                                new ProdutoInputDTO("PS5", new DimensoesDTO(40, 10, 25)),
                                new ProdutoInputDTO("Volante", new DimensoesDTO(40, 30, 30))
                        )
                        )
                ));

        // Act
        EmpacotamentoResponseDTO result = empacotamentoService.empacotarPedidos(request, caixasDisponiveis);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.pedidos().size());
        assertEquals(1L, result.pedidos().get(0).pedido_id());
        assertFalse(result.pedidos().get(0).caixas().isEmpty());
    }

    @Test
    void deveUsarMenorNumeroDeCaixasPossivel() {
        // Arrange
        List<Caixa> caixas = List.of(
                new Caixa("CAIXA_P", 10, 10, 10),
                new Caixa("CAIXA_M", 20, 20, 20)
        );

        PedidoInputDTO pedido = new PedidoInputDTO(1L, List.of(
                new ProdutoInputDTO("Prod1", new DimensoesDTO(5, 5, 5)),
                new ProdutoInputDTO("Prod2", new DimensoesDTO(5, 5, 5))
        ));

        // Act
        PedidoEmpacotadoDTO result = empacotamentoService.empacotarPedido(pedido, caixas);

        // Assert
        assertEquals(1, result.totalCaixasUtilizadas());
    }

    @Test
    void deveRetornarCaixaVaziaSeNenhumProdutoCaber() {
        // Arrange
        List<Caixa> caixas = List.of(new Caixa("CAIXA_P", 1, 1, 1));
        List<ProdutoInputDTO> produtos = List.of(
                new ProdutoInputDTO("ProdGrande", new DimensoesDTO(2, 2, 2))
        );

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            empacotamentoService.encontrarMelhorCaixa(produtos, caixas);
        });
    }
}