package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PedidoDTO;
import com.example.demo.model.Pedido;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
@Tag(name="Pedido", description="Gerenciamento de Pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping
	@Operation(summary="Listar pedidos", description="Retorna uma lista de todos os pedidos")
	public ResponseEntity<List<PedidoDTO>> listar() {
		List<PedidoDTO> produtos = pedidoService.buscarTodos();
		return ResponseEntity.ok(produtos);
	}

	@GetMapping("/{id}")
	@Operation(summary="Buscar pedido por ID", description="Retorna os detalhes de um pedido específico pelo ID")
	public ResponseEntity<PedidoDTO> buscar(@PathVariable Long id) {
		Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
		if (pedidoOpt.isPresent()) {
			PedidoDTO pedidoDTO = new PedidoDTO(pedidoOpt.get());
			return ResponseEntity.ok(pedidoDTO);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary="Inserir novo pedido", description="Cria um novo pedido com os dados fornecidos")
	public PedidoDTO inserir(@RequestBody PedidoDTO pedidoDTO) {
		return pedidoService.inserir(pedidoDTO);
	}

	@PutMapping("/{id}")
	@Operation(summary="Atualizar pedido", description="Atualiza os dados de um pedido existente pelo ID")
	public ResponseEntity<PedidoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PedidoDTO pedidoDTO) {
		PedidoDTO pedidoAtualizado = pedidoService.atualizar(id, pedidoDTO);
		if (pedidoAtualizado != null) {
			return ResponseEntity.ok(pedidoAtualizado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Operation(summary="Deletar pedido", description="Remove um pedido existente pelo ID")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		boolean pedidoExist = pedidoRepository.existsById(id);
		if (pedidoExist) {
			pedidoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}/nota-fiscal")
	@Operation(summary="Gerar nota fiscal", description="Gera uma nota fiscal em PDF para o pedido especificado pelo ID")
	public ResponseEntity<byte[]> gerarNotaFiscal(@PathVariable Long id) throws Exception {
		byte[] pdfBytes = pedidoService.gerarNotaFiscalJson(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment", "nota_fiscal_pedido_" + id + ".pdf");
		return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	}

	@PostMapping("/{id}/nota-fiscal")
	@Operation(summary="Gerar nota fiscal local", description="Gera uma nota fiscal em PDF e salva localmente para o pedido especificado pelo ID")
	public ResponseEntity<String> gerarNotaFiscalLocal(@PathVariable Long id) throws Exception {
		String caminho = pedidoService.gerarNotaFiscalLocal(id);
		return ResponseEntity.ok("Nota fiscal gerada em: " + caminho);
	}
}