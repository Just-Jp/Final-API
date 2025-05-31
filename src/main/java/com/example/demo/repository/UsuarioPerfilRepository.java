package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.profiles.UsuarioPerfil;
import com.example.demo.profiles.UsuarioPerfilPK;

public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, UsuarioPerfilPK> {
}