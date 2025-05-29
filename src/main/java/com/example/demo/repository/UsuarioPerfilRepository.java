package com.example.demo.repository;

import com.example.demo.profiles.UsuarioPerfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, Long> {
}