package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CupomRequestDTO;
import com.example.demo.model.CupomDesconto;
import com.example.demo.service.CupomDescontoService;

@RestController
@RequestMapping("/cupons")
public class CupomDescontoController {

    @Autowired
    private CupomDescontoService service;

    @GetMapping
    public ResponseEntity<List<CupomDesconto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CupomDesconto> buscar(@PathVariable Long id) {
        Optional<CupomDesconto> cupom = service.buscar(id);
        return cupom.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CupomDesconto> criarCupom(@RequestBody CupomRequestDTO dto) {
        return ResponseEntity.ok(service.criarCupom(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/validar/{codigo}")
    public ResponseEntity<CupomDesconto> validarCupom(@PathVariable String codigo) {
        Optional<CupomDesconto> cupom = service.validarCupom(codigo);
        return cupom.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/ativar/{id}")
    public ResponseEntity<CupomDesconto> ativarCupom(@PathVariable Long id) {
        return ResponseEntity.ok(service.ativarCupom(id));
    }

    @PutMapping("/desativar/{id}")
    public ResponseEntity<CupomDesconto> desativarCupom(@PathVariable Long id) {
        return ResponseEntity.ok(service.desativarCupom(id));
    }
}
