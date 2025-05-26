package com.vinicius.empacotamento.domain.dimensoes;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Dimensao {

    private double altura;
    private double largura;
    private double comprimento;

    public double getVolume() {
        return altura * largura * comprimento;
    }
}
