package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.EnderecoDTO;
import com.example.demo.model.Endereco;
import com.example.demo.repository.EnderecoRepository;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository repo;

    // Metodos CRUD
    public List<EnderecoDTO> listar() {
        return repo.findAll().stream()
                .map(EnderecoDTO::new)
                .collect(Collectors.toList());
    }

    public EnderecoDTO buscar(String cep) {
        Optional<Endereco> endereco = repo.findByCep(cep);
        if (endereco.isPresent()) {
            return new EnderecoDTO(endereco.get());

        } else {
            RestTemplate restTemplate = new RestTemplate();
            String uri = "http://viacep.com.br/ws/" + cep + "/json";
            
            Optional<Endereco> enderecoViaCep = Optional.ofNullable(restTemplate.getForObject(uri, Endereco.class));
            if (enderecoViaCep.isPresent() && enderecoViaCep.get().getCep() != null) {
                Optional<Endereco> enderecoExistente = repo.findByCep(enderecoViaCep.get().getCep());
                if (enderecoExistente.isPresent()) {
                    return new EnderecoDTO(enderecoExistente.get());
                } else {
                    Endereco novoEndereco = enderecoViaCep.get();
                    return inserir(novoEndereco);
                }
            } else {
                return null;
            }
        }
    }

    private EnderecoDTO inserir(Endereco endereco) {
        Endereco novoEndereco = repo.save(endereco);
        return new EnderecoDTO(novoEndereco);
    }

    // Funções Extras
    public Endereco buscarCep(String cep) {
        return repo.findByCep(cep).orElse(null);
    }
}