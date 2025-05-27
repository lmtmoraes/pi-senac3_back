package com.salaobeleza.agendaapp.service;

import com.salaobeleza.agendaapp.model.Profissional;
import com.salaobeleza.agendaapp.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public List<Profissional> listarTodos() {
        return profissionalRepository.findAll();
    }

    public Optional<Profissional> buscarPorId(Long id) {
        return profissionalRepository.findById(id);
    }

    public boolean existePorEmail(String email) {
        return profissionalRepository.existsByEmail(email);
    }

    public Profissional salvar(Profissional profissional) {
        return profissionalRepository.save(profissional);
    }

    public void deletar(Long id) {
        profissionalRepository.deleteById(id);
    }
}
