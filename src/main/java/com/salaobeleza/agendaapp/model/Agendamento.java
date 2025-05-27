package com.salaobeleza.agendaapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime dataHora;
    private String descricao;
    private String status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Agendamento() {}

    public Agendamento(LocalDateTime dataHora, String descricao, String status, Usuario usuario) {
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.status = status;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}