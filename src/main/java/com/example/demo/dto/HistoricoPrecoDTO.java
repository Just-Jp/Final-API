package com.example.demo.dto;

import com.example.demo.model.HistoricoPreco;

import java.time.LocalDateTime;

public class HistoricoPrecoDTO {
    private String nomeProduto;
    private Double preco;
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