package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService serv;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {
        return ResponseEntity.ok(serv.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> pesquisarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(serv.buscarPorId(id));
    }

    @GetMapping("cpf/{cpf}")
    public ResponseEntity<ClienteDTO> pesquisarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(serv.buscarPorCpf(cpf));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarId(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO atualizado = serv.atualizar(id, clienteDTO);
        return ResponseEntity.ok(atualizado);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> inserir(@Valid @RequestBody ClienteDTO clienteDTO) {

        ClienteDTO novoDTO = serv.inserir(clienteDTO);

        Cliente novoCliente = serv.buscarCpf(novoDTO.getCpf());
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoCliente.getId())
                .toUri();
        return ResponseEntity.created(uri).body(novoDTO);
    }
}
