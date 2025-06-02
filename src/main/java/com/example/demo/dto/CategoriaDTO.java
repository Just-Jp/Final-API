package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO que representa uma categoria de produto")
public class CategoriaDTO {

	@Schema(description = "ID da categoria")
    private Long id;
    
	@Schema(description = "Nome da categoria")
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
