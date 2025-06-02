package com.example.demo.model;

public class CupomDesconto {

	private Long id;
	private String codigo;
	private Double percentual;
	private String email;
	private Boolean ativo = true;
	
	
	public CupomDesconto(Long id, String codigo, Double percentual, String email, Boolean ativo) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.percentual = percentual;
		this.email = email;
		this.ativo = ativo;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Boolean getAtivo() {
		return ativo;
	}


	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	
}
