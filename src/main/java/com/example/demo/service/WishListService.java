package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.WishListDTO;
import com.example.demo.exception.WishListException;
import com.example.demo.model.Produto;
import com.example.demo.model.WishList;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.WishListRepository;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteRepository clienteRepository;

    public WishListDTO criar(WishListDTO wishListDTO) {
    WishList wishList = new WishList();
    wishList.setId(wishListDTO.getId());
    wishList.setCliente(clienteRepository.findById(wishListDTO.getClienteId()).orElse(null));
    wishList.setProdutos(produtoService.buscarProdutosPorIds(wishListDTO.getProdutosIds()));
    WishList listaSalva = wishListRepository.save(wishList);
    return new WishListDTO(listaSalva);
    }

    public List<WishListDTO> listarPorCliente(Long clienteId) {
    return wishListRepository.findAll().stream()
        .filter(w -> w.getCliente().getId().equals(clienteId))
        .map(wishList -> new WishListDTO(wishList))
        .toList();
}


    public WishListDTO adicionarProdutos(Long wishListId, List<Long> produtosIds) {
        WishList wishList = wishListRepository.findById(wishListId)
                .orElseThrow(() -> new WishListException("WishList não encontrada"));
        List<Produto> produtosAd = produtoService.buscarProdutosPorIds(produtosIds);
        for (Produto produto : produtosAd) {
            if (!wishList.getProdutos().contains(produto)) {
                wishList.getProdutos().add(produto);
            }
        }
        WishList wishListAt = wishListRepository.save(wishList);
        return new WishListDTO(wishListAt);
    }

    public List<WishListDTO> listarTodas() {
    List<WishList> wishLists = wishListRepository.findAll();
    return wishLists.stream()
        .map(wishList -> new WishListDTO(wishList))
        .toList();
    }

    public void deletar(Long id) {
    if (!wishListRepository.existsById(id)) {
        throw new WishListException("WishList não encontrada");
    }
    wishListRepository.deleteById(id);
    }

    public WishListDTO removerProduto(Long wishListId, Long produtoId) {
    WishList wishList = wishListRepository.findById(wishListId)
        .orElseThrow(() -> new WishListException("WishList não encontrada"));

    wishList.getProdutos().removeIf(produto -> produto.getId().equals(produtoId));

    WishList wishListAt = wishListRepository.save(wishList);
    return new WishListDTO(wishListAt);
    }

    
}
