package com.example.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.UsuarioInserirDTO;
import com.example.demo.exception.TratamentoException;
import com.example.demo.mail.MailConfig;
import com.example.demo.model.Cliente;
import com.example.demo.model.Endereco;
import com.example.demo.profiles.Perfil;
import com.example.demo.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoService endServ;

    @Autowired
    private MailConfig mailConfig;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilService perfilService;

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

        Optional<Cliente> clienteCpf = repo.findByCpf(clienteDTO.getCpf());
        if (clienteCpf.isPresent() && !clienteCpf.get().getId().equals(id)) {
            throw new TratamentoException("CPF já cadastrado: " + clienteDTO.getCpf());
        }

        Optional<Cliente> clienteEmail = repo.findByEmail(clienteDTO.getEmail());
        if (clienteEmail.isPresent() && !clienteEmail.get().getId().equals(id)) {
            throw new TratamentoException("Email já cadastrado: " + clienteDTO.getEmail());
        }

        Optional<Cliente> clienteNome = repo.findByNome(clienteDTO.getNome());
        if (clienteNome.isPresent() && !clienteNome.get().getId().equals(id)) {
            throw new TratamentoException("Nome já cadastrado: " + clienteDTO.getNome());
        }

        return repo.findById(id).map(cli -> {
            Endereco endereco = endServ.buscarCep(clienteDTO.getCep());
            if (endereco == null) {
                endServ.buscar(clienteDTO.getCep());
                endereco = endServ.buscarCep(clienteDTO.getCep());
            }
            cli.setNome(clienteDTO.getNome());
            cli.setEmail(clienteDTO.getEmail());
            cli.setTelefone(clienteDTO.getTelefone());
            cli.setCpf(clienteDTO.getCpf());
            cli.setEndereco(endereco);

            mailConfig.sendEmail(cli.getEmail(), "Cadastro alterado com sucesso",
                    "Olá " + cli.getNome() + ",\n\nSeu cadastro foi alterado com sucesso!\n\nLoja Serratec!");
            return new ClienteDTO(repo.save(cli));
        }).orElse(null);
    }

    public ClienteDTO inserir(ClienteDTO clienteDTO) throws RuntimeException {

        Optional<Cliente> cliente = repo.findByCpf(clienteDTO.getCpf());
        if (cliente.isPresent()) {
            throw new TratamentoException("CPF já cadastrado: " + clienteDTO.getCpf());
        }

        cliente = repo.findByEmail(clienteDTO.getEmail());
        if (cliente.isPresent()) {
            throw new TratamentoException("Email já cadastrado: " + clienteDTO.getEmail());
        }

        Cliente novoCliente = new Cliente(clienteDTO);
        Endereco endereco = endServ.buscarCep(clienteDTO.getCep());
        if (endereco == null) {
            endServ.buscar(clienteDTO.getCep());
            endereco = endServ.buscarCep(clienteDTO.getCep());
        }
        novoCliente.setEndereco(endereco);

        Cliente adicionado = repo.save(novoCliente);

        UsuarioInserirDTO usuarioDTO = new UsuarioInserirDTO();
        usuarioDTO.setNome(clienteDTO.getNome());
        usuarioDTO.setEmail(clienteDTO.getEmail());
        usuarioDTO.setSenha(clienteDTO.getCpf());
        usuarioDTO.setConfirmaSenha(clienteDTO.getCpf());

        Set<Perfil> perfis = new HashSet<>();
        Perfil perfilCliente = perfilService.buscarPorNome("CLIENTE");
        perfis.add(perfilCliente);
        usuarioDTO.setPerfis(perfis);

        usuarioService.inserir(usuarioDTO);

        mailConfig.sendEmail(adicionado.getEmail(), "Cadastro realizado com sucesso",
                "Olá " + adicionado.getNome() + ",\n\nSeu cadastro foi realizado com sucesso!\n\nLoja Serratec!");
        return new ClienteDTO(adicionado);
    }

    public void deletar(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new TratamentoException("Cliente não encontrado para exclusão");
        }
    }

    public Cliente buscarCpf(String cpf) {
        return repo.findByCpf(cpf).orElse(null);
    }

}
