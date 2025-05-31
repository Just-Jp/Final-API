package com.example.demo.dto;

public class FidelidadeDTO {
	
	private Long clienteId;
    
	private Integer pontos;

	public FidelidadeDTO(Long clienteId, Integer pontos) {
		super();
		this.clienteId = clienteId;
		this.pontos = pontos;
	}

	public FidelidadeDTO() {
    }
	
	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}
	
	
}
