package com.vinicius.empacotamento.domain.caixa;

import com.vinicius.empacotamento.domain.dimensoes.Dimensao;
import com.vinicius.empacotamento.domain.pedido.Pedido;
import com.vinicius.empacotamento.domain.produto.Produto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "caixas")
@Entity(name = "Caixa")
public class Caixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String nome;

    @Embedded
    private Dimensao dimensao;

    @ManyToMany
    @JoinTable(
            name = "caixa_produto",
            joinColumns = @JoinColumn(name = "caixa_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
