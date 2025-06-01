package com.example.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class HistoricoPreco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long produtoId;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private LocalDateTime dataAlteracao;

    // Getters e Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getProdutoId() {
        return produtoId;
    }
    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }
    public BigDecimal getPreco() {
        return preco;
    }
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }
    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
