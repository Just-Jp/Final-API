package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProdutoDTO;
import com.example.demo.model.Categoria;
import com.example.demo.model.Produto;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<ProdutoDTO> listar() {
        List<ProdutoDTO> produtosDTO = produtoRepository.findAll().stream()
                .map(produto -> new ProdutoDTO(produto))
                .toList();
        return produtosDTO;
    }

    public ProdutoDTO buscar(Long id) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);
        if (produtoOpt.isPresent()) {
            return new ProdutoDTO(produtoOpt.get());
        }
        return null;
    }

    public ProdutoDTO inserir(ProdutoDTO produtoDTO) {
        Produto produto = toEntity(produtoDTO);
        Produto salvo = produtoRepository.save(produto);
        return new ProdutoDTO(salvo);
    }

    public ProdutoDTO atualizar(Long id, ProdutoDTO produtoDTO) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);
        if (produtoOpt.isPresent()) {
            Produto produtoExistente = produtoOpt.get();

            Categoria categoria = categoriaRepository.findByNome(produtoDTO.getCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada: " + produtoDTO.getCategoria()));
                    
            produtoExistente.setNome(produtoDTO.getNome());
            produtoExistente.setPreco(produtoDTO.getPreco());
            produtoExistente.setCategoria(categoria);
            produtoExistente.setDescricao(produtoDTO.getDescricao());
            Produto atualizado = produtoRepository.save(produtoExistente);
            return new ProdutoDTO(atualizado);
        }
        return null;
    }

    public ProdutoDTO deletar(Long id) {
        ProdutoDTO produtoDTO = buscar(id);
        if (produtoDTO != null) {
            produtoRepository.deleteById(id);
            return produtoDTO;
        }
        return null;
    }

    public Produto toEntity(ProdutoDTO dto) {
        Categoria categoria = categoriaRepository.findByNome(dto.getCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada: " + dto.getCategoria()));
        return new Produto(dto, categoria);
    }

    public List<Produto> buscarProdutosPorIds(List<Long> ids) {
    return produtoRepository.findAllById(ids);
}
}
