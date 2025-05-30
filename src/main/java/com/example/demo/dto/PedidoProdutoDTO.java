package com.example.demo.dto;

public class PedidoProdutoDTO {
    private String produto;
    private Integer quantidade;
    private Double valorVenda;
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
