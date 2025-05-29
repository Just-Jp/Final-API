package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.PedidoDTO;
import com.example.demo.model.Pedido;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.ProdutoRepository;

public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoService prodServ;
	
	public List<PedidoDTO> buscarTodos(){
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoDTO> pedidosDto = new ArrayList<>();
		for(Pedido u: pedidos) {
			PedidoDTO uDTO = new PedidoDTO(u);
			pedidosDto.add(uDTO);
		}
		return pedidosDto;
	}
	
	public PedidoDTO inserir(PedidoDTO pedidoDto) {
		Pedido pedido = new Pedido(pedidoDto);
		Pedido pedidoNovo = pedidoRepository.save(pedido);
		return new PedidoDTO(pedidoNovo);
	}
	
	public PedidoDTO buscarPorId(Long id) {
		return pedidoRepository.findById(id)
				.map(PedidoDTO::new)
				.orElse(null);
	}
	
	public void deletar(Long id) {
		Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
		if(pedidoOpt.isPresent()) {
			pedidoRepository.deleteById(id);
		}
		throw new RuntimeException("Produto não encontrado");
	}
		
	public PedidoDTO atualizar(Long id, PedidoDTO pedidoDto) {
		Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
		if(pedidoOpt.isPresent()) {
			Pedido pedido = pedidoOpt.get();
			pedido.setId(id);
			pedido.setProdutos(pedidoDto.getProdutos());
			pedidoRepository.save(pedido);
			PedidoDTO pedidoDto1 = new PedidoDTO(pedido);
			return pedidoDto1;
		}
		throw new RuntimeException("Pedido não encontrado");

	}
	}
	

