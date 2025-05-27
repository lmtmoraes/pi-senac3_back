package com.salaobeleza.agendaapp.repository;

import com.salaobeleza.agendaapp.model.Servico;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

    Optional<Servico> findById(Integer id);
}