package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.EnderecoDTO;
import com.example.demo.exception.CpfException;
import com.example.demo.exception.EmailException;
import com.example.demo.model.Cliente;
import com.example.demo.model.Endereco;
import com.example.demo.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoService endServ;

    // Metodos CRUD
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

    public ClienteDTO buscarPorCpf(String cpf) {
        return repo.findByCpf(cpf)
                .map(ClienteDTO::new)
                .orElse(null);
    }

    public ClienteDTO atualizar(Long id, ClienteDTO clienteDTO) {
        return repo.findById(id).map(
                cli -> {
                    EnderecoDTO novoEndereco = endServ.buscar(clienteDTO.getCep());
                    cli.setNome(clienteDTO.getNome());
                    cli.setEmail(clienteDTO.getEmail());
                    cli.setTelefone(clienteDTO.getTelefone());
                    cli.setCpf(clienteDTO.getCpf());
                    cli.setEndereco(new Endereco(novoEndereco));
                    return new ClienteDTO(repo.save(cli));
                }).orElse(null);
    }

    public ClienteDTO inserir(ClienteDTO clienteDTO) throws RuntimeException {

        Optional<Cliente> cliente = repo.findByCpf(clienteDTO.getCpf());
        if (cliente.isPresent()) {
            throw new CpfException("CPF já cadastrado: " + clienteDTO.getCpf());
        }

        cliente = repo.findByEmail(clienteDTO.getEmail());
        if (cliente.isPresent()) {
            throw new EmailException("Email já cadastrado: " + clienteDTO.getEmail());
        }

        Cliente novoCliente = new Cliente(clienteDTO);
        Endereco endereco = endServ.buscarCep(clienteDTO.getCep());
        if (endereco == null) {
            endServ.buscar(clienteDTO.getCep());
            endereco = endServ.buscarCep(clienteDTO.getCep());
        }
        novoCliente.setEndereco(endereco);

        return new ClienteDTO(repo.save(novoCliente));
    }

    // Funções Extras
    public Cliente buscarCpf(String cpf) {
        return repo.findByCpf(cpf).orElse(null);
    }
}
