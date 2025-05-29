package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.Pedido;
import com.example.demo.model.Produto;
import com.example.demo.model.Status;

public class PedidoDTO {

	private String cliente;
	private List<String> produtos;
	private Status status;
	private LocalDate dataPedido;

	public PedidoDTO(Pedido pedido) {
		this.cliente = pedido.getCliente().getNome();
		this.dataPedido = pedido.getDataPedido();
		this.produtos = pedido.getProdutos().stream()
				.map(Produto::getNome)
				.toList();
		this.status = pedido.getStatus();	
	}

	public PedidoDTO() {}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public List<String> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<String> produtos) {
		this.produtos = produtos;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	
	
}
	
