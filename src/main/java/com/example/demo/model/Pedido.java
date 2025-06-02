package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
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
import jakarta.validation.constraints.NotBlank;

@Entity
@Schema(description = "Entidade que representa um pedido realizado por um cliente")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do pedido", example = "1")
    private Long id;

    @JoinColumn(name = "id_cliente", nullable = false)
    @ManyToOne
    @Schema(description = "Cliente que realizou o pedido")
    private Cliente cliente;
    @Valid
    @Column(nullable = false)

    @NotBlank(message = "A lista de produtos não pode estar vazia")
    @OneToMany
    @Schema(description = "Lista de produtos incluídos no pedido")
    private List<Produto> produtos;

    @Column(nullable = false)
    @Valid
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<PedidoProduto> itens;
  
    @NotBlank(message = "A data do pedido não pode estar vazia")
    @Schema(description = "Data em que o pedido foi realizado")
    @Column(nullable = false)
    private LocalDate dataPedido;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Schema(description = "Status atual do pedido")
    private Status status;
  
    @Column
    private Double valorTotal;
    
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

    public List<PedidoProduto> getItens() {
        return itens;
    }

    public void setItens(List<PedidoProduto> itens) {
        this.itens = itens;
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

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
