package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoriaDTO {
    private Long id;
    
    @NotBlank(message = "O nome da categoria é obrigatório")
    private String nome;
    
    public CategoriaDTO () {};
    
	public CategoriaDTO(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

    
}
