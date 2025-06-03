package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.config.MailConfig;
import com.example.demo.config.NotaFiscalConfig;
import com.example.demo.dto.PedidoDTO;
import com.example.demo.dto.PedidoProdutoDTO;
import com.example.demo.exception.TratamentoException;
import com.example.demo.model.Cliente;
import com.example.demo.model.CupomDesconto;
import com.example.demo.model.Pedido;
import com.example.demo.model.PedidoProduto;
import com.example.demo.model.Produto;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.CupomDescontoRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.security.JwtUtil;

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
	
	@Autowired
	private CupomDescontoRepository cupomRepo;

	@Autowired
	private NotaFiscalConfig notaFiscalConfig;

	public List<PedidoDTO> buscarTodos() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoDTO> pedidosDto = new ArrayList<>();
		for (Pedido pedido : pedidos) {
			pedidosDto.add(new PedidoDTO(pedido));
		}
		return pedidosDto;
	}

	public PedidoDTO buscarPorId(Long id) {
		Pedido pedido = pedidoRepository.findById(id).orElse(null);
		String emailLogado = JwtUtil.getEmailUsuarioLogado();
		boolean isCliente = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
	        .stream().anyMatch(a -> a.getAuthority().equals("ROLE_CLIENTE"));
	    if (isCliente && pedido != null && !pedido.getCliente().getEmail().equals(emailLogado)) {
	        throw new TratamentoException("Acesso negado");
	    }
	    return pedido != null ? new PedidoDTO(pedido) : null;
	}

	public PedidoDTO inserir(PedidoDTO pedidoDto) {
		Pedido pedido = toEntity(pedidoDto);
		if (pedidoDto.getCupom() != null) {
			Double novoTotal = aplicarCupom(pedidoDto.getCupom(), pedido);
			pedido.setValorTotal(novoTotal);
		}
		Pedido pedidoNovo = pedidoRepository.save(pedido);

		mailConfig.sendEmail(pedidoNovo.getCliente().getEmail(), "Pedido realizado com sucesso",
				"Olá " + pedidoNovo.getCliente().getNome()
						+ ",\n\nSeu pedido foi realizado com sucesso!\n\nLoja Serratec!");
		return new PedidoDTO(pedidoNovo);
	}

	public void deletar(Long id) {
		Pedido pedido = pedidoRepository.findById(id).orElse(null);
	    String emailLogado = JwtUtil.getEmailUsuarioLogado();
	    boolean isCliente = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
	        .stream().anyMatch(a -> a.getAuthority().equals("ROLE_CLIENTE"));
	    if (isCliente && pedido != null && !pedido.getCliente().getEmail().equals(emailLogado)) {
	        throw new TratamentoException("Acesso negado");
	    }
		if (pedidoRepository.existsById(id)) {
			pedidoRepository.deleteById(id);
		} else {
			throw new TratamentoException("Pedido não encontrado");
		}
	}

	public PedidoDTO atualizar(Long id, PedidoDTO pedidoDto) {
		Pedido pedidoExistente = pedidoRepository.findById(id).orElse(null);
	    String emailLogado = JwtUtil.getEmailUsuarioLogado();
	    boolean isCliente = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
	        .stream().anyMatch(a -> a.getAuthority().equals("ROLE_CLIENTE"));
	    if (isCliente && pedidoExistente != null && !pedidoExistente.getCliente().getEmail().equals(emailLogado)) {
	        throw new TratamentoException("Acesso negado");
	    }
	    Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
	    if (pedidoOpt.isPresent()) {
	        Pedido pedido = pedidoOpt.get();

	        pedido.getItens().clear();
	        pedidoRepository.saveAndFlush(pedido); 

	        // Atualiza os campos básicos
	        pedido.setCliente(
	                clienteRepository.findByNomeIgnoreCase(pedidoDto.getCliente())
	                        .orElseThrow(
	                                () -> new TratamentoException(
	                                        "Cliente não encontrado: " + pedidoDto.getCliente())));
	        pedido.setDataPedido(pedidoDto.getDataPedido());
	        pedido.setStatus(pedidoDto.getStatus());

	        List<PedidoProduto> itens = new ArrayList<>();
	        if (pedidoDto.getItens() != null) {
	            for (PedidoProdutoDTO itemDto : pedidoDto.getItens()) {
	                Produto produto = produtoRepository.findByNomeIgnoreCase(itemDto.getProduto())
	                        .orElseThrow(
	                                () -> new TratamentoException("Produto não encontrado: " + itemDto.getProduto()));
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

	        pedido.getItens().clear();
			pedido.getItens().addAll(itens);
	        pedido.setValorTotal(calcularValorTotal(itens));

	        if (pedidoDto.getCupom() != null) {
	            Double novoTotal = aplicarCupom(pedidoDto.getCupom(), pedido);
	            pedido.setValorTotal(novoTotal);
	        }

	        mailConfig.sendEmail(pedido.getCliente().getEmail(), "Pedido atualizado com sucesso",
	                "Olá " + pedido.getCliente().getNome()
	                        + ",\n\nSeu pedido foi atualizado com sucesso!\n\nLoja Serratec!");

	        Pedido salvo = pedidoRepository.save(pedido);
	        return new PedidoDTO(salvo);
	    }
	    throw new TratamentoException("Pedido não encontrado");
	}

	public Pedido toEntity(PedidoDTO dto) {
		Cliente cliente = clienteRepository.findByNomeIgnoreCase(dto.getCliente())
				.orElseThrow(() -> new TratamentoException("Cliente não encontrado: " + dto.getCliente()));

		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setDataPedido(dto.getDataPedido());
		pedido.setStatus(dto.getStatus());

		List<PedidoProduto> itens = new ArrayList<>();
		if (dto.getItens() != null) {
			for (PedidoProdutoDTO itemDto : dto.getItens()) {
				Produto produto = produtoRepository.findByNomeIgnoreCase(itemDto.getProduto())
						.orElseThrow(() -> new TratamentoException("Produto não encontrado: " + itemDto.getProduto()));
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

	public String gerarNotaFiscalLocal(Long id) throws Exception {
		PedidoDTO pedido = buscarPorId(id);
		if (pedido == null)
			throw new TratamentoException("Pedido não encontrado");
		String caminho = "nota_fiscal_pedido_" + id + ".pdf";
		notaFiscalConfig.converterPdfArquivo(pedido, caminho);
		return caminho;
	}

	public byte[] gerarNotaFiscalJson(Long id) throws Exception {
		PedidoDTO pedido = buscarPorId(id);
		if (pedido == null)
			throw new TratamentoException("Pedido não encontrado");
		return notaFiscalConfig.converterPdfJson(pedido);
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
  
  public Double aplicarCupom(String codigoCupom, Pedido pedido) {
		Optional<CupomDesconto> cupomOpt = cupomRepo.findByCodigo(codigoCupom);
		if(cupomOpt.isPresent() && cupomOpt.get().getAtivo()) {
			CupomDesconto cupom = cupomOpt.get();

			double novoValor = pedido.getValorTotal() * (1 - cupom.getPercentual() / 100);
			cupomRepo.save(cupom);

			return novoValor;
		}
		throw new RuntimeException("Cupom inválido ou expirado.");
		}
}
