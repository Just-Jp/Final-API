package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CategoriaDTO;
import com.example.demo.exception.TratamentoException;
import com.example.demo.model.Categoria;
import com.example.demo.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public CategoriaDTO salvar(CategoriaDTO dto) {
		if (repository.findByNomeIgnoreCase(dto.getNome()).isPresent()) {
			throw new TratamentoException("Já existe uma categoria com esse nome.");
		}
		Categoria categoria = new Categoria();
		categoria.setNome(dto.getNome());
		categoria = repository.save(categoria);
		dto.setId(categoria.getId());
		return dto;
	}

	public CategoriaDTO atualizar(Long id, CategoriaDTO dto) {
        Categoria categoria = repository.findById(id)
            	.orElseThrow(() -> new TratamentoException("Categoria não encontrada para atualização"));
        if (repository.findByNomeIgnoreCase(dto.getNome()).isPresent()) {
            throw new TratamentoException("Já existe uma categoria com esse nome.");
        }
        categoria.setNome(dto.getNome());
        categoria = repository.save(categoria);
        return toDTO(categoria);
    }

	public List<CategoriaDTO> listarTodas() {
		return repository.findAll()
				.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}

	public CategoriaDTO buscarPorId(Long id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new TratamentoException("Categoria não encontrada"));
        return toDTO(categoria);
    }

	public CategoriaDTO buscarPorNome(String nome) {
        Categoria categoria = repository.findByNomeIgnoreCase(nome)
            .orElseThrow(() -> new TratamentoException("Categoria não encontrada"));
        return toDTO(categoria);
    }

	public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new TratamentoException("Categoria não encontrada para exclusão");
        }
        repository.deleteById(id);
    }

	private CategoriaDTO toDTO(Categoria categoria) {
		CategoriaDTO dto = new CategoriaDTO();
		dto.setId(categoria.getId());
		dto.setNome(categoria.getNome());
		return dto;
	}
}
