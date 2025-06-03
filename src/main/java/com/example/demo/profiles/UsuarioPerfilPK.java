package com.example.demo.profiles;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

@Embeddable
@Schema(description="Chave primária composta para a associação entre usuário e perfil")
public class UsuarioPerfilPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @Schema(description="Usuário associado ao perfil")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_perfil")
    @Schema(description="Perfil associado ao usuário")
    private Perfil perfil;

    public int hashCode() {
        return Objects.hash(perfil, usuario);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UsuarioPerfilPK other = (UsuarioPerfilPK) obj;
        return Objects.equals(perfil, other.perfil) && Objects.equals(usuario, other.usuario);
    }

    public UsuarioPerfilPK() {
    }

    public UsuarioPerfilPK(Usuario usuario, Perfil perfil) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}

