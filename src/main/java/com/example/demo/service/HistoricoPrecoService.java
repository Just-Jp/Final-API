package com.example.demo.service;

import com.example.demo.repository.HistoricoPrecoRepository;
import com.example.model.HistoricoPreco;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoricoPrecoService {

    private final HistoricoPrecoRepository historicoPrecoRepository;

    public HistoricoPrecoService(HistoricoPrecoRepository historicoPrecoRepository) {
        this.historicoPrecoRepository = historicoPrecoRepository;
    }

    public HistoricoPreco criarHistorico(Long produtoId, BigDecimal preco) {
        HistoricoPreco historico = new HistoricoPreco();
        historico.setProdutoId(produtoId);
        historico.setPreco(preco);
        historico.setDataAlteracao(LocalDateTime.now());
        return historicoPrecoRepository.save(historico);
    }

    public List<HistoricoPreco> buscarPorProduto(Long produtoId) {
        return historicoPrecoRepository.findByProdutoIdOrderByDataAlteracaoDesc(produtoId);
    }
}