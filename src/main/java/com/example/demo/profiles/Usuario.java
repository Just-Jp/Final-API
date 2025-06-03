package com.example.demo.profiles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Schema(description="Entidade que representa um usuário do sistema")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    @Schema(description="ID do usuário")
    private Long id;

    @Column(nullable = false)
    @Schema(description="Nome do usuário")
    private String nome;

    @Column(nullable = false, unique = true)
    @Schema(description="Email do usuário")
    private String email;

    @Column(nullable = false)
    @Length(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    @Schema(description="Senha do usuário")
    private String senha;

    @OneToMany(mappedBy = "id.usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Schema(description="Perfis associados ao usuário")
    private Set<UsuarioPerfil> usuarioPerfis = new HashSet<>();

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UsuarioPerfil usuarioPerfil : this.usuarioPerfis) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + usuarioPerfil.getId().getPerfil().getNome()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<UsuarioPerfil> getUsuarioPerfis() {
        return usuarioPerfis;
    }

    public void setUsuarioPerfis(Set<UsuarioPerfil> usuarioPerfis) {
        this.usuarioPerfis = usuarioPerfis;
    }
}