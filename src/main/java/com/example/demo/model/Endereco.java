package com.example.demo.model;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import com.example.demo.dto.EnderecoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
@Schema(description = "Entidade que representa o endereço do cliente")
public class Endereco {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do endereço")
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "O CEP não pode estar vazio")
    @Schema(description = "Código de Endereçamento Postal")
    private String cep;

    @Column(nullable = false)
    @NotBlank(message = "O logradouro não pode estar vazio")
    @Schema(description = "Nome da rua ou avenida")
    private String logradouro;

    @Column(nullable = false)
    @Schema(description = "Complemento do endereço", example = "lado ímpar")
    private String complemento;

    @Column(nullable = false)
    @NotBlank(message = "O bairro não pode estar vazio")
    @Schema(description = "Bairro do endereço")
    private String bairro;

    @Column(nullable = false)
    @NotBlank(message = "A localidade não pode estar vazia")
    @Schema(description = "Cidade ou município")
    private String localidade;

    @Column(nullable = false)
    @NotBlank(message = "O UF não pode estar vazio")
    @Length(min = 2, max = 2, message = "O UF deve ter exatamente 2 caracteres")
    @Schema(description = "Unidade Federativa (UF)")
    private String uf;

    public Endereco() {}

    public Endereco(EnderecoDTO endereco) {
        this.cep = endereco.getCep();
        this.logradouro = endereco.getLogradouro();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.localidade = endereco.getLocalidade();
        this.uf = endereco.getUf();
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
