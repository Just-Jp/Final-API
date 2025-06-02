package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO que representa o programa de fidelidade de um cliente")
public class FidelidadeDTO {
	
	@Schema(description = "ID do cliente")
	private Long clienteId;
    
	@Schema(description = "Quantidade de pontos acumulados pelo cliente")
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
