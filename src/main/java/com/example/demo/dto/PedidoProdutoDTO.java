package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="DTO que representa as credenciais de login de um usuário")
public class PedidoProdutoDTO {
    
    @Schema(description="Nome do produto no pedido")
    private String produto;
    
    @Schema(description="Quantidade do produto no pedido")
    private Integer quantidade;
    
    @Schema(description="Valor de venda do produto no pedido")
    private Double valorVenda;
    
    @Schema(description="Desconto aplicado ao produto no pedido")
    private Double desconto;
    
    public PedidoProdutoDTO() {}

    public PedidoProdutoDTO(String produto, Integer quantidade, Double valorVenda, Double desconto) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorVenda = valorVenda;
        this.desconto = desconto;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
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
}
