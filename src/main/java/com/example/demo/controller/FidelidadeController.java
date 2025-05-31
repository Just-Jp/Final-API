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

	@RestController
	@RequestMapping("/api/fidelidade")
	public class FidelidadeController {

	    @Autowired
	    private FidelidadeService service;

	    @PostMapping("/criar/{clienteId}")
	    public ResponseEntity<FidelidadeDTO> criar(@PathVariable Long clienteId) {
	        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarPrograma(clienteId));
	    }

	    @GetMapping("/{clienteId}")
	    public ResponseEntity<FidelidadeDTO> consultar(@PathVariable Long clienteId) {
	        return ResponseEntity.ok(service.consultarPontos(clienteId));
	    }

	    @PostMapping("/{clienteId}/ganhar/{pontos}")
	    public ResponseEntity<FidelidadeDTO> ganharPontos(@PathVariable Long clienteId, @PathVariable Integer pontos) {
	        return ResponseEntity.ok(service.adicionarPontos(clienteId, pontos));
	    }
}

