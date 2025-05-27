package com.salaobeleza.agendaapp.controller;

import com.salaobeleza.agendaapp.model.Agendamento;
import com.salaobeleza.agendaapp.repository.AgendamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @GetMapping
    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarPorId(@PathVariable Long id) {
        return agendamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Agendamento criar(@RequestBody Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizar(@PathVariable Long id, @RequestBody Agendamento dadosAtualizados) {
        return agendamentoRepository.findById(id).map(agendamento -> {
            agendamento.setDataHora(dadosAtualizados.getDataHora());
            agendamento.setDescricao(dadosAtualizados.getDescricao());
            agendamento.setStatus(dadosAtualizados.getStatus());
            agendamento.setUsuario(dadosAtualizados.getUsuario());
            return ResponseEntity.ok(agendamentoRepository.save(agendamento));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return agendamentoRepository.findById(id).map(agendamento -> {
            agendamentoRepository.delete(agendamento);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}