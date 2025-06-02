package com.example.demo.dto;
import jakarta.validation.constraints.NotBlank;

public class CupomRequestDTO {
	@NotBlank(message = "Codigo é necessario")
	private String codigo;
	private Double desconto;
	private Boolean ativo = true;
	
	public CupomRequestDTO() {}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}	
}
