package com.example.demo.model;

import com.example.demo.dto.CupomRequestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Schema(description="Entidade que representa um cupom de desconto")
public class CupomDesconto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description="ID do cupom de desconto")
	private Long id;

	@Column(nullable = false, unique = true)
	@Schema(description="Código do cupom de desconto", example="DESCONTO10")
	private String codigo;
	
	@Min(value = 0, message = "O desconto deve ser no mínimo 0")
	@Max(value = 100, message = "O desconto deve ser no máximo 100")
	@Schema(description="Percentual de desconto do cupom")
	private Double percentual;
	
	@Schema(description="Indica se o cupom está ativo ou não")
	private Boolean ativo = true;

	public CupomDesconto() {
	}
	
	public CupomDesconto(Long id, String codigo, Double percentual, Boolean ativo) {
		this.id = id;
		this.codigo = codigo;
		this.percentual = percentual;
		this.ativo = ativo;
	}

	public CupomDesconto(CupomRequestDTO dto) {
		this.codigo = dto.getCodigo();
		this.percentual = dto.getDesconto() != null ? dto.getDesconto() : 0.0;
		this.ativo = dto.getAtivo() != null ? dto.getAtivo() : true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Double getPercentual() {
		return percentual;
	}

	public void setPercentual(Double percentual) {
		this.percentual = percentual;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
