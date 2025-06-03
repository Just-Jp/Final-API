package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CupomRequestDTO;
import com.example.demo.model.CupomDesconto;
import com.example.demo.service.CupomDescontoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cupons")
@Tag(name="cupom", description="Gerenciamento de cupons de desconto")
public class CupomDescontoController {

    @Autowired
    private CupomDescontoService service;

    @GetMapping
    @Operation(summary="Listar todos os cupons", description="Retorna uma lista de todos os cupons de desconto cadastrados")
    public ResponseEntity<List<CupomDesconto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary="Buscar cupom por ID", description="Retorna os detalhes de um cupom de desconto específico pelo ID")
    public ResponseEntity<CupomDesconto> buscar(@PathVariable Long id) {
        Optional<CupomDesconto> cupom = service.buscar(id);
        return cupom.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary="Criar novo cupom", description="Cria um novo cupom de desconto")
    public ResponseEntity<CupomDesconto> criarCupom(@RequestBody CupomRequestDTO dto) {
        return ResponseEntity.ok(service.criarCupom(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Deletar cupom", description="Remove um cupom de desconto pelo ID")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/validar/{codigo}")
    @Operation(summary="Validar cupom", description="Valida um cupom de desconto pelo código")
    public ResponseEntity<CupomDesconto> validarCupom(@PathVariable String codigo) {
        Optional<CupomDesconto> cupom = service.validarCupom(codigo);
        return cupom.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/ativar/{id}")
    @Operation(summary="Ativar cupom", description="Ativa um cupom de desconto pelo ID")
    public ResponseEntity<CupomDesconto> ativarCupom(@PathVariable Long id) {
        return ResponseEntity.ok(service.ativarCupom(id));
    }

    @PutMapping("/desativar/{id}")
    @Operation(summary="Desativar cupom", description="Desativa um cupom de desconto pelo ID")
    public ResponseEntity<CupomDesconto> desativarCupom(@PathVariable Long id) {
        return ResponseEntity.ok(service.desativarCupom(id));
    }
}
