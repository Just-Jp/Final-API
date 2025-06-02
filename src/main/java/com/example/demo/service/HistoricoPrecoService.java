package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.HistoricoPrecoDTO;
import com.example.demo.exception.TratamentoException;
import com.example.demo.model.HistoricoPreco;
import com.example.demo.model.Produto;
import com.example.demo.repository.HistoricoPrecoRepository;
import com.example.demo.repository.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;

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

    public List<HistoricoPrecoDTO> buscarPorProduto(Long produtoId) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new TratamentoException("Produto não encontrado com ID: " + produtoId));

        List<HistoricoPreco> historicos = historicoPrecoRepository.findByProdutoOrderByDataAlteracaoDesc(produto);

        return historicos.stream()
                .map(HistoricoPrecoDTO::new)
                .collect(Collectors.toList());
    }
}