package com.salaobeleza.agendaapp.service;

import com.salaobeleza.agendaapp.model.Servico;
import com.salaobeleza.agendaapp.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    public Servico salvar(Servico servico) {
        return servicoRepository.save(servico);
    }

    public Optional<Servico> buscarPorId(Long id) {
        return servicoRepository.findById(id);
    }

    public Optional<Servico> atualizar(Long id, Servico dadosAtualizados) {
        return servicoRepository.findById(id).map(servico -> {
            servico.setNome(dadosAtualizados.getNome());
            servico.setDescricao(dadosAtualizados.getDescricao());
            servico.setPreco(dadosAtualizados.getPreco());
            servico.setTipo(dadosAtualizados.getTipo());
            return servicoRepository.save(servico);
        });
    }

    public boolean deletar(Long id) {
        return servicoRepository.findById(id).map(servico -> {
            servicoRepository.delete(servico);
            return true;
        }).orElse(false);
    }
}
