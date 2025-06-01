package com.example.demo.controller;

import com.example.demo.model.HistoricoPreco;
import com.example.demo.service.HistoricoPrecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/historico-precos")
public class HistoricoPrecoController {

    @Autowired
    private HistoricoPrecoService historicoPrecoService;

    public HistoricoPrecoController(HistoricoPrecoService historicoPrecoService) {
        this.historicoPrecoService = historicoPrecoService;
    }

    @PostMapping
    public ResponseEntity<HistoricoPreco> criarHistorico(
            @RequestParam Long produtoId,
            @RequestParam BigDecimal preco) {
        HistoricoPreco historico = historicoPrecoService.criarHistorico(produtoId, preco);
        return ResponseEntity.ok(historico);
    }

    @GetMapping
    public ResponseEntity<List<HistoricoPreco>> buscarHistorico(@RequestParam Long produtoId) {
        List<HistoricoPreco> historicos = historicoPrecoService.buscarPorProduto(produtoId);
        return ResponseEntity.ok(historicos);
    }
}