package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@Schema(description = "Entidade que representa o programa de fidelidade do cliente")
public class Fidelidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Identificador único do cliente (mesmo valor do ID do cliente)")
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "cliente_id")
    @Schema(description = "Referência ao cliente participante do programa de fidelidade")
    private Cliente cliente;

    @Schema(description = "Quantidade de pontos acumulados no programa de fidelidade")
    private Integer pontos = 0;
    
    public Fidelidade() {
    }
    
	public Fidelidade(Long clienteId, Cliente cliente, Integer pontos) {
		this.id = clienteId;
		this.cliente = cliente;
		this.pontos = pontos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
