package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PedidoDTO;
import com.example.demo.model.Cliente;
import com.example.demo.model.Pedido;
import com.example.demo.model.Produto;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<PedidoDTO> buscarTodos() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoDTO> pedidosDto = new ArrayList<>();
		for (Pedido u : pedidos) {
			PedidoDTO uDTO = new PedidoDTO(u);
			pedidosDto.add(uDTO);
		}
		return pedidosDto;
	}

	public PedidoDTO buscarPorId(Long id) {
		return pedidoRepository.findById(id)
				.map(PedidoDTO::new)
				.orElse(null);
	}

	public PedidoDTO inserir(PedidoDTO pedidoDto) {
		Pedido pedido = toEntity(pedidoDto);
		Pedido pedidoNovo = pedidoRepository.save(pedido);
		return new PedidoDTO(pedidoNovo);
	}

	public void deletar(Long id) {
		Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
		if (pedidoOpt.isPresent()) {
			pedidoRepository.deleteById(id);
		}
		throw new RuntimeException("Produto não encontrado");
	}

	public PedidoDTO atualizar(Long id, PedidoDTO pedidoDto) {
    Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
    if (pedidoOpt.isPresent()) {
        Pedido pedidoAtualizado = toEntity(pedidoDto);
        Pedido salvo = pedidoRepository.save(pedidoAtualizado);
        return new PedidoDTO(salvo);
    }
    throw new RuntimeException("Pedido não encontrado");
}

	public Pedido toEntity(PedidoDTO dto) {
		Cliente cliente = clienteRepository.findByNome(dto.getCliente())
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado: " + dto.getCliente()));

		List<Produto> produtos = dto.getProdutos().stream()
				.map(nome -> produtoRepository.findByNome(nome)
						.orElseThrow(() -> new RuntimeException("Produto não encontrado: " + nome)))
				.toList();
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setProdutos(produtos);
		pedido.setDataPedido(dto.getDataPedido());
		pedido.setStatus(dto.getStatus());
		return pedido;
	}

}
