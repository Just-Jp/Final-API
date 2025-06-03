package com.example.demo.dto;

import java.util.Set;

import com.example.demo.profiles.Perfil;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="DTO para inserir um usuário")
public class UsuarioInserirDTO {
    
    @Schema(description="Nome do usuário")
    private String nome;
    
    @Schema(description="Email do usuário")
    private String email;
    
    @Schema(description="Senha do usuário")
    private String senha;
    
    @Schema(description="Confirmação da senha do usuário")
    private String confirmaSenha;
    
    @Schema(description="Perfis do usuário")
    private Set<Perfil> perfis;
    
    
    public Set<Perfil> getPerfis() {
        return perfis;
    }
    public void setPerfis(Set<Perfil> perfis) {
        this.perfis = perfis;
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
