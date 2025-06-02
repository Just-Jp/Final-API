package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Fidelidade;

@Repository
public interface FidelidadeRepository extends JpaRepository<Fidelidade, Long> {
}
