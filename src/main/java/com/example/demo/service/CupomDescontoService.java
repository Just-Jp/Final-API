package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.CupomDesconto;
import com.example.demo.repository.CupomDescontoRepository;

public class CupomDescontoService {

	@Autowired
	private CupomDescontoRepository cupomDescRepo;
	
	public Optional<CupomDesconto> validarCupom(String codigo){
		return cupomDescRepo.findByCodigo(codigo).filter(CupomDesconto::getAtivo);
	}


	
	public CupomDesconto ativarCupom(Long id) {
		CupomDesconto cupom = cupomDescRepo.findById(id).orElseThrow(() -> new RuntimeException("Cupom não encontrado"));
		cupom.setAtivo(true);
		return cupomDescRepo.save(cupom);
	}

	public CupomDesconto desativarCupom(Long id) {
		CupomDesconto cupom = cupomDescRepo.findById(id).orElseThrow(() -> new RuntimeException("Cupom não encontrado"));
		cupom.setAtivo(false);
		return cupomDescRepo.save(cupom);
	}
}
