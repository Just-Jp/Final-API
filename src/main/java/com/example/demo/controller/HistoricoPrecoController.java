package com.example.demo.controller;

import com.example.demo.model.HistoricoPreco;
import com.example.demo.service.HistoricoPrecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico-precos")
public class HistoricoPrecoController {

    @Autowired
    private HistoricoPrecoService historicoPrecoService;

    @PostMapping
    public ResponseEntity<HistoricoPreco> criarHistorico(@RequestBody HistoricoPreco historicoPreco) {
        HistoricoPreco historico = historicoPrecoService.criarHistorico(
                historicoPreco.getProduto().getId(),
                historicoPreco.getPreco()
        );
        return ResponseEntity.ok(historico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<HistoricoPreco>> buscarHistorico(@PathVariable Long id) {
        List<HistoricoPreco> historicos = historicoPrecoService.buscarPorProduto(id);
        return ResponseEntity.ok(historicos);
    }
}