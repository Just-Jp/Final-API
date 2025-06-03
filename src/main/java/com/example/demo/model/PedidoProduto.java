package com.example.demo.model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
@Schema(description="Entidade que representa a relação entre um pedido e um produto")
public class PedidoProduto implements Serializable {
    
    @EmbeddedId
    @Schema(description="ID composto entre pedido e produto")
    private PedidoProdutoId id = new PedidoProdutoId();

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id")
    @Schema(description="Pedido ao qual o produto pertence")
    private Pedido pedido;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    @Schema(description="Produto incluído no pedido")
    private Produto produto;

    @Schema(description="Valor de venda do produto no pedido")
    private Double valorVenda;

    @Schema(description="Desconto aplicado ao produto no pedido")
    private Double desconto;

    @Schema(description="Quantidade do produto no pedido")
    private Integer quantidade;

    public PedidoProduto() {}

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
        this.id.setPedidoId(pedido.getId());
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        this.id.setProdutoId(produto.getId());
    }

    public PedidoProdutoId getId() {
        return id;
    }

    public void setId(PedidoProdutoId id) {
        this.id = id;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}