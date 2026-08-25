package com.algaworks.AprendizadoSpring.domain.repository;

import com.algaworks.AprendizadoSpring.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
