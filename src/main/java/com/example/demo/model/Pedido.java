package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "id_cliente", nullable = false)
    @Valid
    @NotNull(message = "O cliente não pode estar vazio")
    @ManyToOne
    private Cliente cliente;

    @Column(nullable = false)
    @Valid
    @NotEmpty(message = "A lista de produtos não pode estar vazia")
    @OneToMany
    private List<Produto> produtos;

    @Column(nullable = false)
    @Valid
    @NotNull(message = "A data do pedido não pode estar vazia")
    private LocalDate dataPedido;

    @Column(nullable = false)
    @Valid
    @NotNull(message = "O status do pedido não pode estar vazio")
    @Enumerated(EnumType.STRING)
    private Status status;
    
    public Pedido() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }  

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
