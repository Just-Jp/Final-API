package com.example.demo.model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "O CEP não pode estar vazio")
    private String cep;

    @Column(nullable = false)
    @NotBlank(message = "O logradouro não pode estar vazio")
    private String logradouro;

    @Column(nullable = false)
    @NotBlank(message = "O complemento não pode estar vazio")
    private String complemento;

    @Column(nullable = false)
    @NotBlank(message = "O bairro não pode estar vazio")
    private String bairro;

    @Column(nullable = false)
    @NotBlank(message = "A localidade não pode estar vazia")
    private String localidade;

    @Column(nullable = false)
    @NotBlank(message = "O UF não pode estar vazio")
    @Length(min = 2, max = 2, message = "O UF deve ter exatamente 2 caracteres")
    private String uf;

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
