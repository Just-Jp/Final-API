package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EnderecoDTO;
import com.example.demo.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoService serv;

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> listar() {
        return ResponseEntity.ok(serv.listar());
    }

    @GetMapping("{cep}")
    public ResponseEntity<EnderecoDTO> buscar(@PathVariable String cep) {
        EnderecoDTO enderecoDTO = serv.buscar(cep);
        if (enderecoDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(enderecoDTO);
        }
    }
}