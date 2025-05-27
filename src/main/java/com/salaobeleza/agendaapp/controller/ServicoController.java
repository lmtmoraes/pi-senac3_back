package com.salaobeleza.agendaapp.controller;

import com.salaobeleza.agendaapp.model.Servico;
import com.salaobeleza.agendaapp.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping
    public ResponseEntity<List<Servico>> listarTodos() {
        return ResponseEntity.ok(servicoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable Long id) {
        Optional<Servico> servico = servicoService.buscarPorId(id);
        return servico.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Servico> cadastrar(@RequestBody Servico servico) {
        return ResponseEntity.status(201).body(servicoService.salvar(servico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(@PathVariable Long id, @RequestBody Servico dadosAtualizados) {
        Optional<Servico> atualizado = servicoService.atualizar(id, dadosAtualizados);
        return atualizado.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean removido = servicoService.deletar(id);
        return removido ? ResponseEntity.noContent().build()
                        : ResponseEntity.notFound().build();
    }
}
