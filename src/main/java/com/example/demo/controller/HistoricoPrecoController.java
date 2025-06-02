package com.example.demo.controller;

import com.example.demo.dto.HistoricoPrecoDTO;
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

    @GetMapping("/{id}")
    public ResponseEntity<List<HistoricoPrecoDTO>> buscarHistoricoPorProduto(@PathVariable Long id) {
        List<HistoricoPrecoDTO> historicoDTOs = historicoPrecoService.buscarPorProduto(id);
        return ResponseEntity.ok(historicoDTOs);
    }
}