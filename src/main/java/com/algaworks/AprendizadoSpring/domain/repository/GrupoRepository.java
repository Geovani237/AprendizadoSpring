package com.algaworks.AprendizadoSpring.domain.repository;

import com.algaworks.AprendizadoSpring.domain.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo,Long> {
}
