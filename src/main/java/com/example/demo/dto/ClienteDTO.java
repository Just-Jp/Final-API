package com.example.demo.dto;

import com.example.demo.model.Cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description="DTO que representa um cliente")
public class ClienteDTO {
    @NotBlank(message = "O nome do cliente não pode estar vazio")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "Nome inválido")
    @Schema(description="Nome do cliente")
    private String nome;

    @NotBlank(message = "O email do cliente não pode estar vazio")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Email inválido")
    @Schema(description="Email do cliente")
    private String email;

    @NotBlank(message = "O telefone do cliente não pode estar vazio")
    @Pattern(regexp = "^\\(?(\\d{2})\\)? ?(\\d{4,5}-?\\d{4})$", message = "Telefone inválido")
    @Schema(description="Telefone do cliente")
    private String telefone;

    @NotBlank(message = "O cep do cliente não pode estar vazio")
    @Schema(description="CEP do cliente")
    private String cep;

    @NotBlank(message = "O CPF do cliente não pode estar vazio")
    @Pattern(regexp = "^(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})$", message = "CPF inválido")
    @Schema(description="CPF do cliente")
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
