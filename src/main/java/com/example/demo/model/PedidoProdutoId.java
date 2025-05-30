package com.example.demo.model;

import java.io.Serializable;
import jakarta.persistence.Embeddable;

@Embeddable
public class PedidoProdutoId implements Serializable {
    private Long pedidoId;
    private Long produtoId;

    public PedidoProdutoId() {}

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }
}
