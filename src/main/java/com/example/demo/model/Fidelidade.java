package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Fidelidade {

	@Id
    private Long clienteId;

    
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private Integer pontos = 0;
    
    public Fidelidade() {
    }
    
	public Fidelidade(Long clienteId, Cliente cliente, Integer pontos) {
		super();
		this.clienteId = clienteId;
		this.cliente = cliente;
		this.pontos = pontos;
	}




	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}
    
    
}
