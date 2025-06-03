package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="DTO que representa as credenciais de login de um usuário")
public class LoginDTO {
    
    @Schema(description="Nome de usuário para autenticação")
    private String username;
    
    @Schema(description="Senha para autenticação")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}