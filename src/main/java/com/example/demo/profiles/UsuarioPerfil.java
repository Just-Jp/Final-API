package com.example.demo.profiles;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario_perfil")
@Schema(description="Entidade que representa a associação entre um usuário e um perfil")
public class UsuarioPerfil {

    @EmbeddedId
    @Schema(description="ID que representa a associação entre usuário e perfil")
    private UsuarioPerfilPK id = new UsuarioPerfilPK();

    @Column(name = "data-criacao")
    @Schema(description="Data de criação da associação entre usuário e perfil")
    private LocalDate dataCriacao;

    public UsuarioPerfil() {}

    public UsuarioPerfil(Usuario usuario, Perfil perfil, LocalDate dataCriacao) {
        this.id.setUsuario(usuario);
        this.id.setPerfil(perfil);
        this.dataCriacao = dataCriacao;
    }

    public UsuarioPerfilPK getId() {
        return id;
    }
    public void setId(UsuarioPerfilPK id) {
        this.id = id;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setUsuario(Usuario usuario) {
        this.id.setUsuario(usuario);
    }

    public void setPerfil(Perfil perfil) {
        this.id.setPerfil(perfil);
    }

}
