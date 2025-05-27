package com.example.demo.dto;

import com.example.demo.model.Cliente;

public class ClienteDTO {
    private String nome;
    private String email;
    private String telefone;
    private String cep;
    private String cpf;

    public ClienteDTO() {
    }

    public ClienteDTO(String nome, String email, String telefone, String cep, String cpf) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cep = cep;
        this.cpf = cpf;
    }

    public ClienteDTO(Cliente cliente) {
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.telefone = cliente.getTelefone();
        this.cep = cliente.getEndereco().getCep();
        this.cpf = cliente.getCpf();
    }

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
}
