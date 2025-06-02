package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProdutoDTO;
import com.example.demo.exception.TratamentoException;
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

    @Autowired
    private HistoricoPrecoService precoServ;

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
        categoriaRepository.findByNome(produtoDTO.getCategoria())
                .orElseThrow(() -> new TratamentoException("Categoria não encontrada: " + produtoDTO.getCategoria()));
        Produto salvo = produtoRepository.save(produto);
        precoServ.criarHistorico(salvo.getId(), salvo.getPreco());

        return new ProdutoDTO(salvo);
    }

    public ProdutoDTO atualizar(Long id, ProdutoDTO produtoDTO) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);
        if (produtoOpt.isPresent()) {
            Produto produtoExistente = produtoOpt.get();

            Categoria categoria = categoriaRepository.findByNome(produtoDTO.getCategoria())
                    .orElseThrow(() -> new TratamentoException("Categoria não encontrada: " + produtoDTO.getCategoria()));

            produtoExistente.setNome(produtoDTO.getNome());
            produtoExistente.setPreco(produtoDTO.getPreco());
            produtoExistente.setCategoria(categoria);
            produtoExistente.setDescricao(produtoDTO.getDescricao());

            Produto atualizado = produtoRepository.save(produtoExistente);
            precoServ.criarHistorico(atualizado.getId(), atualizado.getPreco());
            return new ProdutoDTO(atualizado);
        }
        throw new TratamentoException("Produto não encontrado para atualização");
    }

    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new TratamentoException("Produto não encontrado");
        }
        produtoRepository.deleteById(id);
    }

    public ProdutoDTO inativar(Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow();
        produto.setAtivo(false);
        produtoRepository.save(produto);
        return new ProdutoDTO(produto);
    }
    
    public ProdutoDTO reativar(Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow();
        produto.setAtivo(true);
        produtoRepository.save(produto);
        return new ProdutoDTO(produto);
    }
    
    public List<ProdutoDTO> listarAtivo() {
        List<Produto> produtos = produtoRepository.findByAtivo(true);
        return produtos.stream()
                .map(ProdutoDTO::new)
                .collect(Collectors.toList());
    }

    public Produto toEntity(ProdutoDTO dto) {
        Categoria categoria = categoriaRepository.findByNome(dto.getCategoria())
                .orElseThrow(() -> new TratamentoException("Categoria não encontrada: " + dto.getCategoria()));
        return new Produto(dto, categoria, true);
    }

    public List<Produto> buscarProdutosPorIds(List<Long> ids) {
        return produtoRepository.findAllById(ids);
    }
}