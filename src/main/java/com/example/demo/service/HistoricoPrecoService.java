package com.example.demo.service;

import com.example.demo.model.HistoricoPreco;
import com.example.demo.model.Produto;
import com.example.demo.repository.HistoricoPrecoRepository;
import com.example.demo.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoricoPrecoService {

    @Autowired
    private HistoricoPrecoRepository historicoPrecoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public HistoricoPreco criarHistorico(Long produtoId, Double preco) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + produtoId));

        HistoricoPreco historico = new HistoricoPreco();
        historico.setProduto(produto);
        historico.setPreco(preco);
        historico.setDataAlteracao(LocalDateTime.now());

        return historicoPrecoRepository.save(historico);
    }

    public List<HistoricoPreco> buscarPorProduto(Long produtoId) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com ID: " + produtoId));

        return historicoPrecoRepository.findByProdutoOrderByDataAlteracaoDesc(produto);
    }
}