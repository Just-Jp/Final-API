package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ClienteInserirDTO {
    @NotBlank(message = "O nome do cliente não pode estar vazio")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "Nome inválido")
    private String nome;

    @NotBlank(message = "O email do cliente não pode estar vazio")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Email inválido")
    private String email;

    @NotBlank(message = "O telefone do cliente não pode estar vazio")
    @Pattern(regexp = "^\\(?(\\d{2})\\)? ?(\\d{4,5}-?\\d{4})$", message = "Telefone inválido")
    private String telefone;

    @NotBlank(message = "O cep do cliente não pode estar vazio")
    private String cep;

    @NotBlank(message = "O CPF do cliente não pode estar vazio")
    @Pattern(regexp = "^(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})$", message = "CPF inválido")
    private String cpf;

    @NotBlank(message = "A senha não pode estar vazia")
    private String senha;

    @NotBlank(message = "A confirmação de senha não pode estar vazia")
    private String confirmaSenha;

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }
}
