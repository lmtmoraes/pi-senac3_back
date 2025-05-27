package com.salaobeleza.agendaapp.repository;

import com.salaobeleza.agendaapp.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    boolean existsByEmail(String email);
}
