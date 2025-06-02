package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CupomDesconto;
import com.example.demo.service.CupomDescontoService;

@RestController
@RequestMapping("/cupons")
public class DescontoController {

	@Autowired
	private CupomDescontoService CupomDescServ;
	
	@PostMapping("/ativar/{id}")
	public ResponseEntity<CupomDesconto> ativarCupom(@PathVariable Long id) {
		CupomDesconto cupom = CupomDescServ.ativarCupom(id);
		return ResponseEntity.ok(cupom);
	}

	@PostMapping("/desativar/{id}")
	public ResponseEntity<CupomDesconto> desativarCupom(@PathVariable Long id) {
		CupomDesconto cupom = CupomDescServ.desativarCupom(id);
		return ResponseEntity.ok(cupom);
	}

	@GetMapping("/validar")
	public ResponseEntity<CupomDesconto> validarCupom(@RequestParam String codigo) {
		return CupomDescServ.validarCupom(codigo)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}
