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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/wishlists")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @PostMapping
    public ResponseEntity<WishListDTO> criar(@Valid @RequestBody WishListDTO wishListDTO) {
        WishListDTO nova = wishListService.criar(wishListDTO);
        return ResponseEntity.ok(nova);
    }

    @GetMapping
    public ResponseEntity<List<WishListDTO>> listarTodas() {
        return ResponseEntity.ok(wishListService.listarTodas());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<WishListDTO>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(wishListService.listarPorCliente(clienteId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        wishListService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/adicionar-produto")
    public ResponseEntity<WishListDTO> adicionarProdutos(
            @PathVariable Long id,
            @Valid @RequestBody List<Long> produtosIds) {
        WishListDTO dto = wishListService.adicionarProdutos(id, produtosIds);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/remover-produto/{produtoId}")
    public ResponseEntity<WishListDTO> removerProduto(
            @PathVariable Long id,
            @Valid @PathVariable Long produtoId) {
        WishListDTO dto = wishListService.removerProduto(id, produtoId);
        return ResponseEntity.ok(dto);
    }
}