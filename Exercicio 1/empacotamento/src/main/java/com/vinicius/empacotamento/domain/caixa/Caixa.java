package com.vinicius.empacotamento.domain.caixa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "caixa")
public class Caixa {
    @Id
    @Column(name = "codigo")
    private String codigo;

    @Column(name = "altura", nullable = false)
    private double altura;

    @Column(name = "largura", nullable = false)
    private double largura;

    @Column(name = "comprimento", nullable = false)
    private double comprimento;

    @Column(name = "volume_maximo", insertable = false, updatable = false)
    private double volumeMaximo;

    public Caixa(String codigo, double altura, double largura, double comprimento) {
        this.codigo = codigo;
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
    }

    @PrePersist
    @PreUpdate
    private void calcularVolume() {
        this.volumeMaximo = altura * largura * comprimento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public double getVolumeMaximo() {
        return volumeMaximo;
    }

    public void setVolumeMaximo(double volumeMaximo) {
        this.volumeMaximo = volumeMaximo;
    }
}