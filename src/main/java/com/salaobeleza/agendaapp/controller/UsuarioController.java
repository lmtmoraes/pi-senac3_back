package com.salaobeleza.agendaapp.controller;

import com.salaobeleza.agendaapp.model.Usuario;
import com.salaobeleza.agendaapp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> buscarUsuario(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @PostMapping
    public Usuario adicionarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.salvar(usuario);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioService.deletar(id);
    }

    // ✅ NOVO MÉTODO: Atualizar um usuário existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        return usuarioService.buscarPorId(id).map(usuario -> {
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setTelefone(usuarioAtualizado.getTelefone());
            Usuario atualizado = usuarioService.salvar(usuario);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.notFound().build());
    }
}
