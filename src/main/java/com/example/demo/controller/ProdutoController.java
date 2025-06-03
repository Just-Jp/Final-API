package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProdutoDTO;
import com.example.demo.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/produtos")
@Tag(name="produto", description="Gerenciamento de produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @Operation(summary="Inserir produto", description="Cria um novo produto no sistema")
    public ResponseEntity<ProdutoDTO> inserir(@Valid @RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO novoProduto = produtoService.inserir(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @GetMapping
    @Operation(summary="Listar produtos", description="Lista todos os produtos ativos no sistema")
    public ResponseEntity<List<ProdutoDTO>> listar() {
        return ResponseEntity.ok(produtoService.listarAtivo());
    }

    @GetMapping("/completo")
    @Operation(summary="Listar todos os produtos", description="Lista todos os produtos, incluindo inativos")
    public ResponseEntity<List<ProdutoDTO>> listarCompleto() {
        return ResponseEntity.ok(produtoService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary="Buscar produto por ID", description="Busca um produto específico pelo seu ID")
    public ResponseEntity<ProdutoDTO> buscar(@PathVariable Long id) {
        ProdutoDTO produto = produtoService.buscar(id);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Operation(summary="Atualizar produto", description="Atualiza as informações de um produto existente")
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO produtoAtualizado = produtoService.atualizar(id, produtoDTO);
        if (produtoAtualizado != null) {
            return ResponseEntity.ok(produtoAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Deletar produto", description="Remove um produto do sistema")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ProdutoDTO produtoDTO = produtoService.buscar(id);
        if (produtoDTO != null) {
            produtoService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/inativar/{id}")
    @Operation(summary="Inativar produto", description="Marca um produto como inativo no sistema")
    public ResponseEntity<ProdutoDTO> inativar(@PathVariable Long id) {
        ProdutoDTO produtoDTO = produtoService.inativar(id);
        return ResponseEntity.ok(produtoDTO);
    }
    
    @PutMapping("/reativar/{id}")
    @Operation(summary="Reativar produto", description="Marca um produto como ativo no sistema")
    public ResponseEntity<ProdutoDTO> reativar(@PathVariable Long id) {
        ProdutoDTO produtoDTO = produtoService.reativar(id);
        return ResponseEntity.ok(produtoDTO);
    }
}