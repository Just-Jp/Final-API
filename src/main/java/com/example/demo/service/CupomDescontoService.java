package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CupomRequestDTO;
import com.example.demo.model.CupomDesconto;
import com.example.demo.repository.CupomDescontoRepository;

@Service
public class CupomDescontoService {

	@Autowired
	private CupomDescontoRepository cupomDescRepo;
	
	public List<CupomDesconto> listar() {
		return cupomDescRepo.findAll();
	}
	
	public Optional<CupomDesconto> buscar(Long id) {
		return cupomDescRepo.findById(id);
	}
	
	public void deletar(Long id) {
		cupomDescRepo.deleteById(id);
	}
	
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

    public CupomDesconto criarCupom(CupomRequestDTO dto) {
        CupomDesconto novoCupom = new CupomDesconto(dto);
        return cupomDescRepo.save(novoCupom);
    }
    
    
}
