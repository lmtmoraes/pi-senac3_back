package com.salaobeleza.agendaapp.repository;

import com.salaobeleza.agendaapp.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
