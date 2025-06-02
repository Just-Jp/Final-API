package com.example.demo.model;

import org.hibernate.validator.constraints.Length;

import com.example.demo.dto.CupomRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CupomDesconto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String codigo;
	@Length(min = 1, max = 10, message = "O desconto deve ser entre 1% e 10%")
	private Double percentual;
	private Boolean ativo = true;
	
	
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
