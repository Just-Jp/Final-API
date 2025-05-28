package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.EnderecoDTO;
import com.example.demo.model.Cliente;
import com.example.demo.model.Endereco;
import com.example.demo.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoService endServ;

    public List<ClienteDTO> listar() {
        return repo.findAll().stream()
                .map(ClienteDTO::new)
                .collect(Collectors.toList());
    }

    public ClienteDTO buscarPorId(Long id) {
        return repo.findById(id)
                .map(ClienteDTO::new)
                .orElse(null);
    }

    public ClienteDTO atualizar(Long id, ClienteDTO clienteDTO) {
        return repo.findById(id).map(
                cli -> {
                    EnderecoDTO novoEndereco = endServ.buscar(clienteDTO.getCep());
                    cli = new Cliente(clienteDTO);
                    cli.setId(id);
                    cli.setEndereco(new Endereco(novoEndereco));
                    return new ClienteDTO(repo.save(cli));
                }).orElse(null);
    }
}
