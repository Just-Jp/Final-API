package com.example.demo.dto;

import com.example.demo.model.Pedido;

import jakarta.validation.constraints.NotBlank;

public class CupomRequestDTO {

	private String email;
	
	@NotBlank(message = "Codigo é necessario")
	private String codigo;
	
	private Pedido pedido;
	
	public CupomRequestDTO() {}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
}
