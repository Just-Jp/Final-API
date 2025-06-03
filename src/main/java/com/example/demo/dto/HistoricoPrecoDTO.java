package com.example.demo.dto;

import com.example.demo.model.HistoricoPreco;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description="DTO que representa o histórico de preços de um produto")
public class HistoricoPrecoDTO {
    
    @Schema(description="Nome do produto")
    private String nomeProduto;

    @Schema(description="Preço do produto")
    private Double preco;

    @Schema(description="Data e hora da alteração de preço")
    private LocalDateTime dataAlteracao;

    public HistoricoPrecoDTO(String nomeProduto, Double preco, LocalDateTime dataAlteracao) {
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.dataAlteracao = dataAlteracao;
    }

    public HistoricoPrecoDTO() {
    }

    public HistoricoPrecoDTO(HistoricoPreco historicoPreco) {
        this.nomeProduto = historicoPreco.getProduto().getNome();
        this.preco = historicoPreco.getPreco();
        this.dataAlteracao = historicoPreco.getDataAlteracao();
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}