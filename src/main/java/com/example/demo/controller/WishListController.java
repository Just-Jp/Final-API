package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.WishListDTO;
import com.example.demo.service.WishListService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/wishlists")
@Tag(name="wishlist", description="Gerenciamento de WishLists")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @PostMapping
    @Operation(summary="Cria WishLists", description="Cria novas WishLists")
    public ResponseEntity<WishListDTO> criar(@Valid @RequestBody WishListDTO wishListDTO) {
        WishListDTO nova = wishListService.criar(wishListDTO);
        return ResponseEntity.ok(nova);
    }

    @GetMapping
    @Operation(summary="Lista todas as WishLists", description="Lista todas as WishLists existentes")
    public ResponseEntity<List<WishListDTO>> listarTodas() {
        return ResponseEntity.ok(wishListService.listarTodas());
    }

    @GetMapping("/cliente/{clienteId}")
    @Operation(summary="Listar WishLists por cliente", description="Listar WishLists pelo ID do cliente")
    public ResponseEntity<List<WishListDTO>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(wishListService.listarPorCliente(clienteId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Deletar WishLists", description="Deleta WishLists existentes")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        wishListService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/adicionar-produto")
    @Operation(summary="Adiciona produtos na WishList", description="Adiciona produtos na WishList pelo Id do produto")
    public ResponseEntity<WishListDTO> adicionarProdutos(
            @PathVariable Long id,
            @Valid @RequestBody List<Long> produtosIds) {
        WishListDTO dto = wishListService.adicionarProdutos(id, produtosIds);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/remover-produto/{produtoId}")
    @Operation(summary="Remove produtos da WishList", description="Remove produtos da WishList por Id do produto")
    public ResponseEntity<WishListDTO> removerProduto(
            @PathVariable Long id,
            @Valid @PathVariable Long produtoId) {
        WishListDTO dto = wishListService.removerProduto(id, produtoId);
        return ResponseEntity.ok(dto);
    }
}