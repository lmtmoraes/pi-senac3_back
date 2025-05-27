package com.salaobeleza.agendaapp.controller;

import com.salaobeleza.agendaapp.model.Profissional;
import com.salaobeleza.agendaapp.service.ProfissionalService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    @GetMapping
    public ResponseEntity<List<Profissional>> listar() {
        return ResponseEntity.ok(profissionalService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Profissional profissional) {
        // Validação manual dos campos obrigatórios
        if (profissional.getNome() == null || profissional.getNome().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("O nome é obrigatório.");
        }
        if (profissional.getEmail() == null || profissional.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("O email é obrigatório.");
        }
        if (profissional.getSenha() == null || profissional.getSenha().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("A senha é obrigatória.");
        }

        Profissional salvo = profissionalService.salvar(profissional);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> buscarPorId(@PathVariable Long id) {
        Profissional profissional = profissionalService.buscarPorId(id).orElse(null);
        if (profissional != null) {
            return ResponseEntity.ok(profissional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Profissional profissionalAtualizado) {
        return profissionalService.buscarPorId(id).map(profissional -> {
            // Validações para atualização (opcional, pode ser igual ao POST)
            if (profissionalAtualizado.getNome() == null || profissionalAtualizado.getNome().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("O nome é obrigatório.");
            }
            if (profissionalAtualizado.getEmail() == null || profissionalAtualizado.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("O email é obrigatório.");
            }
            if (profissionalAtualizado.getSenha() == null || profissionalAtualizado.getSenha().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("A senha é obrigatória.");
            }
            // Atualiza os campos
            profissional.setNome(profissionalAtualizado.getNome());
            profissional.setEmail(profissionalAtualizado.getEmail());
            profissional.setSenha(profissionalAtualizado.getSenha());
            profissional.setTelefone(profissionalAtualizado.getTelefone());
            profissional.setEspecialidade(profissionalAtualizado.getEspecialidade());

            Profissional atualizado = profissionalService.salvar(profissional);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            profissionalService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
