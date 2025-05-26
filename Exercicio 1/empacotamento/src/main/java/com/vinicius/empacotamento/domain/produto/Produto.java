package com.vinicius.empacotamento.domain.produto;

import com.vinicius.empacotamento.domain.dimensoes.Dimensoes;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "produtos")
@Entity(name = "Produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String nome;

    @Embedded
    private Dimensoes dimensoes;
}
