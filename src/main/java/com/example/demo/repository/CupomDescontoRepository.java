package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CupomDesconto;

public interface CupomDescontoRepository extends JpaRepository<CupomDesconto, Long> {
	Optional<CupomDesconto> findByCodigoAndEmail(String codigo, String email);
}
