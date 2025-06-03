package com.example.demo.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.ClienteInserirDTO;
import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
@Tag(name="cliente", description="Gerenciamento de clientes")
public class ClienteController {

    @Autowired
    private ClienteService serv;

    @GetMapping
    @Operation(summary="Listar todos os clientes", description="Retorna uma lista de todos os clientes cadastrados")
    public ResponseEntity<List<ClienteDTO>> listar() {
        return ResponseEntity.ok(serv.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary="Pesquisar cliente por ID", description="Retorna os detalhes de um cliente específico pelo ID")
    public ResponseEntity<ClienteDTO> pesquisarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(serv.buscarPorId(id));
    }

    @GetMapping("cpf/{cpf}")
    @Operation(summary="Pesquisar cliente por CPF", description="Retorna os detalhes de um cliente específico pelo CPF")
    public ResponseEntity<ClienteDTO> pesquisarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(serv.buscarPorCpf(cpf));
    }

    @PutMapping("/{id}")
    @Operation(summary="Atualizar cliente", description="Atualiza os dados de um cliente existente")
    public ResponseEntity<ClienteDTO> atualizarId(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO atualizado = serv.atualizar(id, clienteDTO);
        return ResponseEntity.ok(atualizado);
    }

    @PostMapping
    @Operation(summary="Inserir novo cliente", description="Cria um novo cliente com os dados fornecidos")
    public ResponseEntity<ClienteDTO> inserir(@Valid @RequestBody ClienteInserirDTO clienteInserirDTO) {
        ClienteDTO novoDTO = serv.inserir(clienteInserirDTO);

        Cliente novoCliente = serv.buscarCpf(novoDTO.getCpf());
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoCliente.getId())
                .toUri();
        return ResponseEntity.created(uri).body(novoDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Excluir cliente", description="Remove um cliente do sistema pelo ID")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        serv.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
