package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.Cliente;
import com.example.demo.model.Pedido;
import com.example.demo.model.Produto;
import com.example.demo.model.Status;

public class PedidoDTO {

	private Cliente cliente;
	private List<Produto> produtos;
	private Status status;
	private LocalDate dataPedido;

	public PedidoDTO(Pedido pedido) {
		this.cliente = pedido.getCliente();
		this.dataPedido = pedido.getDataPedido();
		this.produtos = pedido.getProdutos();
		this.status = pedido.getStatus();	
	}

	public PedidoDTO() {}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
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
	
