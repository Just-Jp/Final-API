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
import com.example.demo.exception.TratamentoException;
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
            throw new TratamentoException("Senha e Confirma Senha não são iguais");
        }
        if (usuarioRepository.findByEmail(usuarioInserirDTO.getEmail()).isPresent()) {
            throw new TratamentoException("Email já cadastrado");
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

    @Transactional
    public UsuarioDTO atualizar(Long id, UsuarioInserirDTO usuarioAtualizarDTO) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new TratamentoException("Usuário não encontrado"));

        if (!usuarioAtualizarDTO.getSenha().equals(usuarioAtualizarDTO.getConfirmaSenha())) {
            throw new TratamentoException("Senha e Confirma Senha não são iguais");
        }
        if (usuarioRepository.findByEmail(usuarioAtualizarDTO.getEmail()) != null) {
            throw new TratamentoException("Email já cadastrado");
        }

        usuario.setNome(usuarioAtualizarDTO.getNome());
        usuario.setEmail(usuarioAtualizarDTO.getEmail());
        usuario.setSenha(encoder.encode(usuarioAtualizarDTO.getSenha()));

        Set<UsuarioPerfil> usuarioPerfis = new HashSet<>();
        for (Perfil perfil : usuarioAtualizarDTO.getPerfis()) {
            perfil = perfilService.buscar(perfil.getId());
            UsuarioPerfil usuarioPerfil = new UsuarioPerfil(usuario, perfil, LocalDate.now());
            usuarioPerfis.add(usuarioPerfil);
        }
        usuario.setUsuarioPerfis(usuarioPerfis);

        usuario = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario);
    }

    @Transactional
    public void deletar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new TratamentoException("Usuário não encontrado"));
        usuarioRepository.delete(usuario);
    }

    @Transactional
    public void deletarPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }

    public void atualizarEmail(String emailAntigo, String emailNovo) {
    Usuario usuario = usuarioRepository.findByEmail(emailAntigo)
        .orElseThrow(() -> new TratamentoException("Usuário não encontrado para atualizar e-mail"));
    usuario.setEmail(emailNovo);
    usuarioRepository.save(usuario);
    }

    public boolean existePorEmail(String email) {
        return usuarioRepository.findByEmail(email).isPresent();
    }

    public UsuarioDTO buscar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new TratamentoException("Usuário não encontrado"));
        return new UsuarioDTO(usuario);
    }
}
