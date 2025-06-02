package com.example.demo.model;

import java.util.List;


import io.swagger.v3.oas.annotations.media.Schema;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.ClienteInserirDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Schema(description = "Entidade que representa um cliente do sistema")
public class Cliente {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do cliente", example = "1")
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "O nome do cliente não pode estar vazio")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "Nome inválido")
    @Schema(description = "Nome completo do cliente")
    private String nome;

    @Column(nullable = false)
    @NotBlank(message = "O email do cliente não pode estar vazio")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Email inválido")
    @Schema(description = "Endereço de e-mail do cliente")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "O telefone do cliente não pode estar vazio")
    @Pattern(regexp = "^\\(?(\\d{2})\\)? ?(\\d{4,5}-?\\d{4})$", message = "Telefone inválido")
    @Schema(description = "Número de telefone do cliente")
    private String telefone;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "O CPF do cliente não pode estar vazio")
    @Pattern(regexp = "^(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})$", message = "CPF inválido")
    @Schema(description = "CPF do cliente")
    private String cpf;

    @Schema(description = "Endereço associado ao cliente")
    @JoinColumn(name = "id_endereço", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Endereco endereco;

    @Column
    @OneToMany(mappedBy = "cliente")
    @Schema(description = "Lista de pedidos associados ao cliente")
    private List<Pedido> pedidos;

    public Cliente() {}
    
    public Cliente(ClienteDTO cliente) {
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.telefone = cliente.getTelefone();
        this.cpf = cliente.getCpf();
    }

    public Cliente(ClienteInserirDTO cliente) {
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.telefone = cliente.getTelefone();
        this.cpf = cliente.getCpf();
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

}
