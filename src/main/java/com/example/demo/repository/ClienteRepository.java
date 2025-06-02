package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByNome(String nome);
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByCpf(String cpf);
    Optional<Cliente> findByTelefone(String telefone);
}
