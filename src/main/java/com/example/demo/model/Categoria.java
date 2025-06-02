package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Entidade que representa uma categoria de produtos")
@Entity
public class Categoria {
	
    @Id
    @Schema(description = "Identificador único da categoria", example = "1")
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @Valid
    @NotBlank(message = "O nome da categoria não pode estar vazio")
    @Schema(description = "Nome da categoria")
    private String nome;

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
