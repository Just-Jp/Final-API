package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.dto.UsuarioInserirDTO;
import com.example.demo.exception.EmailException;
import com.example.demo.exception.SenhaException;
import com.example.demo.profiles.Perfil;
import com.example.demo.profiles.Usuario;
import com.example.demo.profiles.UsuarioPerfil;
import com.example.demo.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private PasswordEncoder encoder;

    public List<UsuarioDTO> buscarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            UsuarioDTO usuDTO = new UsuarioDTO(usuario);
            usuariosDTO.add(usuDTO);
        }
        return usuariosDTO;
    }

    @Transactional
    public UsuarioDTO inserir(UsuarioInserirDTO usuarioInserirDTO) {
        if (!usuarioInserirDTO.getSenha().equals(usuarioInserirDTO.getConfirmaSenha())) {
            throw new SenhaException("Senha e Confirma Senha não são iguais");
        }
        if (usuarioRepository.findByEmail(usuarioInserirDTO.getEmail()) != null) {
            throw new EmailException("Email já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioInserirDTO.getNome());
        usuario.setEmail(usuarioInserirDTO.getEmail());
        usuario.setSenha(encoder.encode(usuarioInserirDTO.getSenha()));

        Set<UsuarioPerfil> usuarioPerfis = new HashSet<>();
        for (Perfil perfil : usuarioInserirDTO.getPerfis()) {
            perfil = perfilService.buscar(perfil.getId());
            UsuarioPerfil usuarioPerfil = new UsuarioPerfil(usuario, perfil, LocalDate.now());
            usuarioPerfis.add(usuarioPerfil);
        }

        usuario.setUsuarioPerfis(usuarioPerfis);
        usuario = usuarioRepository.save(usuario);

        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
        return usuarioDTO;
    }
}
