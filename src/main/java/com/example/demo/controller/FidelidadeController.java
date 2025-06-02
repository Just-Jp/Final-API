package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.FidelidadeDTO;
import com.example.demo.service.FidelidadeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

	@RestController
	@RequestMapping("/api/fidelidade")
	@Tag(name = "Fidelidade", description = "Operações relacionadas ao programa de fidelidade")
	public class FidelidadeController {

	    @Autowired
	    private FidelidadeService service;

	    @Operation(summary = "Criar programa de fidelidade para um cliente")
	    @PostMapping("/criar/{clienteId}")
	    public ResponseEntity<FidelidadeDTO> criar(@PathVariable Long clienteId) {
	        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarPrograma(clienteId));
	    }

	    @Operation(summary = "Consultar pontos de fidelidade do cliente")
	    @GetMapping("/{clienteId}")
	    public ResponseEntity<FidelidadeDTO> consultar(@PathVariable Long clienteId) {
	        return ResponseEntity.ok(service.consultarPontos(clienteId));
	    }

	    @Operation(summary = "Adicionar pontos de fidelidade para o cliente")
	    @PostMapping("/{clienteId}/ganhar/{pontos}")
	    public ResponseEntity<FidelidadeDTO> ganharPontos(@PathVariable Long clienteId, @PathVariable Integer pontos) {
	        return ResponseEntity.ok(service.adicionarPontos(clienteId, pontos));
	    }
}

