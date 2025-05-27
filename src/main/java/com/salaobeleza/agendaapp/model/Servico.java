package com.salaobeleza.agendaapp.model;

import jakarta.persistence.*;

@Entity
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private double preco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoServico tipo;

    public Servico() {}

    public Servico(String nome, String descricao, double preco, TipoServico tipo) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public TipoServico getTipo() {
        return tipo;
    }

    public void setTipo(TipoServico tipo) {
        this.tipo = tipo;
    }

    public enum TipoServico {
        CORTE,
        BARBA
    }
}
