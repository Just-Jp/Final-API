package com.example.demo.dto;

import java.util.List;


import com.example.demo.model.Produto;
import com.example.demo.model.WishList;

import jakarta.validation.constraints.NotNull;

public class WishListDTO {
    private Long id;
    
    @NotNull(message = "O clienteId é obrigatório")
    private Long clienteId;
    
    @NotNull(message = "Os produtosIDS são obrigatórios")
    private List<Long> produtosIds;

     public WishListDTO() {
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getClienteId() {
        return clienteId;
    }
    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
    public List<Long> getProdutosIds() {
        return produtosIds;
    }
    public void setProdutosIds(List<Long> produtosIds) {
        this.produtosIds = produtosIds;
    }

    public WishListDTO(WishList wishList) {
    this.id = wishList.getId();
    this.clienteId = wishList.getCliente() != null ? wishList.getCliente().getId() : null;
    this.produtosIds = wishList.getProdutos() != null ? wishList.getProdutos().stream()
            .map(Produto::getId).toList() : null;
}

    
}
