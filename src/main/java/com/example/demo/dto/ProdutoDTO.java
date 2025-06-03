package com.example.demo.dto;

import com.example.demo.model.Produto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="DTO que representa um produto")
public class ProdutoDTO {
    
    @Schema(description="Nome do produto")
    private String nome;
    
    @Schema(description="Preço do produto")
    private Double preco;
    
    @Schema(description="Categoria do produto")
    private String categoria;
    
    @Schema(description="Descrição do produto")
    private String descricao;

    public ProdutoDTO() {
    }
    
    public ProdutoDTO(String nome, Double preco, String categoria, String descricao) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.descricao = descricao;
    }

    public ProdutoDTO(Produto produto) {
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.categoria = produto.getCategoria().getNome();
        this.descricao = produto.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
