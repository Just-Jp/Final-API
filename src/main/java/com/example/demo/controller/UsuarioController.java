package com.example.demo.controller;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.dto.UsuarioInserirDTO;
import com.example.demo.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;
    
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar(
            @AuthenticationPrincipal UserDetails details) {
        System.out.println("Login do usuario: " + details.getUsername());
        List<UsuarioDTO> usuarios = usuarioService.buscarTodos();
        return ResponseEntity.ok(usuarios);
    }
    
    @PostMapping
    public ResponseEntity<UsuarioDTO> inserir(@RequestBody UsuarioInserirDTO usuInsDTO) {
        UsuarioDTO usuarioDTO = usuarioService.inserir(usuInsDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()               // localhost:8080/usuarios
                .path("/{id}")                 // localhost:8080/usuarios/{id}
                .buildAndExpand(usuarioDTO.getId()) // localhost:8080/usuarios/1
                .toUri();
        return ResponseEntity.created(uri).body(usuarioDTO);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id, @RequestBody UsuarioInserirDTO usuarioAtualizarDTO) {
        UsuarioDTO usuarioDTO = usuarioService.atualizar(id, usuarioAtualizarDTO);
        return ResponseEntity.ok(usuarioDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);
        if (usuarioDTO != null) {
            usuarioService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
}