package com.example.demo.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description="DTO que representa um cupom de desconto para inserção")
public class CupomRequestDTO {
	
	@NotBlank(message = "Codigo é necessario")
	@Schema(description="Código do cupom de desconto")
	private String codigo;

	@Schema(description="Valor do desconto do cupom")
	private Double desconto;

	@Schema(description="Indica se o cupom está ativo ou não")
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
