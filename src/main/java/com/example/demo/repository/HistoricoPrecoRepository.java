package com.example.demo.repository;

import com.example.demo.model.HistoricoPreco;
import com.example.demo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoPrecoRepository extends JpaRepository<HistoricoPreco, Long> {

    List<HistoricoPreco> findByProdutoOrderByDataAlteracaoDesc(Produto produto);
    void deleteAllByProdutoId(Long produtoId);
}