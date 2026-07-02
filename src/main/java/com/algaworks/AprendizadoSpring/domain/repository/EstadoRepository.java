package com.algaworks.AprendizadoSpring.domain.repository;

import com.algaworks.AprendizadoSpring.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> listar();
    Estado buscar(Long id);
    Estado salvar(Estado estado);
    void remover(Estado estado);
}
