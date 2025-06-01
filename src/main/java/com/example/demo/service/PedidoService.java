package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PedidoDTO;
import com.example.demo.dto.PedidoProdutoDTO;
import com.example.demo.mail.MailConfig;
import com.example.demo.model.Cliente;
import com.example.demo.model.Pedido;
import com.example.demo.model.PedidoProduto;
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

	@Autowired
	private MailConfig mailConfig;


	public List<PedidoDTO> buscarTodos() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoDTO> pedidosDto = new ArrayList<>();
		for (Pedido pedido : pedidos) {
			pedidosDto.add(new PedidoDTO(pedido));
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

		mailConfig.sendEmail(pedidoNovo.getCliente().getEmail(), "Pedido realizado com sucesso",
				"Olá " + pedidoNovo.getCliente().getNome()
						+ ",\n\nSeu pedido foi realizado com sucesso!\n\nLoja Serratec!");

		return new PedidoDTO(pedidoNovo);
	}

	public void deletar(Long id) {
		if (pedidoRepository.existsById(id)) {
			pedidoRepository.deleteById(id);
		} else {
			throw new CategoriaException("Pedido não encontrado");
		}
	}

	public PedidoDTO atualizar(Long id, PedidoDTO pedidoDto) {
		Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
		if (pedidoOpt.isPresent()) {
			Pedido pedidoExistente = pedidoOpt.get();

			// Atualiza os campos básicos
			pedidoExistente.setCliente(
					clienteRepository.findByNome(pedidoDto.getCliente())
							.orElseThrow(
									() -> new NomeException("Cliente não encontrado: " + pedidoDto.getCliente())));
			pedidoExistente.setDataPedido(pedidoDto.getDataPedido());
			pedidoExistente.setStatus(pedidoDto.getStatus());
			pedidoExistente.getItens().clear();

			List<PedidoProduto> itens = new ArrayList<>();
			if (pedidoDto.getItens() != null) {
				for (PedidoProdutoDTO itemDto : pedidoDto.getItens()) {
					Produto produto = produtoRepository.findByNome(itemDto.getProduto())
							.orElseThrow(() -> new CategoriaException("Produto não encontrado: " + itemDto.getProduto()));
					PedidoProduto pedidoProduto = new PedidoProduto();
					pedidoProduto.setPedido(pedidoExistente);
					pedidoProduto.setProduto(produto);
					pedidoProduto.setQuantidade(itemDto.getQuantidade());
					pedidoProduto.setDesconto(itemDto.getDesconto());

					// (Preco / Desconto) * Quantidade
					double valorVenda = calcularDesconto(produto.getPreco(), itemDto.getDesconto());

					pedidoProduto.setValorVenda(valorVenda * itemDto.getQuantidade());
					itens.add(pedidoProduto);
				}
			}
			pedidoExistente.setItens(itens);
			pedidoExistente.setValorTotal(calcularValorTotal(itens));

			mailConfig.sendEmail(pedidoExistente.getCliente().getEmail(), "Pedido atualizado com sucesso",
					"Olá " + pedidoExistente.getCliente().getNome()
							+ ",\n\nSeu pedido foi atualizado com sucesso!\n\nLoja Serratec!");

			Pedido salvo = pedidoRepository.save(pedidoExistente);
			return new PedidoDTO(salvo);
		}
		throw new CategoriaException("Pedido não encontrado");
	}

	public Pedido toEntity(PedidoDTO dto) {
		Cliente cliente = clienteRepository.findByNome(dto.getCliente())
				.orElseThrow(() -> new NomeException("Cliente não encontrado: " + dto.getCliente()));

		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setDataPedido(dto.getDataPedido());
		pedido.setStatus(dto.getStatus());

		List<PedidoProduto> itens = new ArrayList<>();
		if (dto.getItens() != null) {
			for (PedidoProdutoDTO itemDto : dto.getItens()) {
				Produto produto = produtoRepository.findByNome(itemDto.getProduto())
						.orElseThrow(() -> new CategoriaException("Produto não encontrado: " + itemDto.getProduto()));
				PedidoProduto pedidoProduto = new PedidoProduto();
				pedidoProduto.setPedido(pedido);
				pedidoProduto.setProduto(produto);
				pedidoProduto.setQuantidade(itemDto.getQuantidade());
				pedidoProduto.setDesconto(itemDto.getDesconto());

				double valorVenda = calcularDesconto(produto.getPreco(), itemDto.getDesconto());
				pedidoProduto.setValorVenda(valorVenda * itemDto.getQuantidade());
				itens.add(pedidoProduto);
			}
		}
		pedido.setItens(itens);
		pedido.setValorTotal(calcularValorTotal(itens));
		return pedido;
	}

	public double calcularDesconto(double preco, double desconto) {
		if (desconto < 0 || desconto > 100) {
			throw new IllegalArgumentException("Desconto deve estar entre 0 e 100");
		}
		return preco * (1 - desconto / 100);
	}

	public double calcularValorTotal(List<PedidoProduto> itens) {
		if (itens == null)
			return 0.0;
		return itens.stream()
				.mapToDouble(PedidoProduto::getValorVenda)
				.sum();
	}

}
