package com.example.demo.model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;

@Embeddable
@Schema(description="ID composto entre pedido e produto")
public class PedidoProdutoId implements Serializable {
    
    @Schema(description="ID do pedido")
    private Long pedidoId;
    
    @Schema(description="ID do produto")
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
