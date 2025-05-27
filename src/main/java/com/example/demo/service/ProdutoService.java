package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listar() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos;
    }

    public Produto buscar(Long id) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);
        if (produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get();
            return produto;
        }
        return null;
    }

    public Produto inserir(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto atualizar (Long id, Produto produto) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);
        if (produtoOpt.isPresent()) {
            Produto produtoExistente = produtoOpt.get();
            produtoExistente.setNome(produto.getNome());
            produtoExistente.setPreco(produto.getPreco());
            produtoExistente.setCategoria(produto.getCategoria());
            produtoExistente.setDescricao(produto.getDescricao());
            return produtoRepository.save(produtoExistente);
        }
        return null;
    }

    public void deletar(Long id) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);
        if (produtoOpt.isPresent()) {
            produtoRepository.deleteById(id);
        }
    }
}

