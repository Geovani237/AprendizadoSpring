package com.algaworks.AprendizadoSpring.domain.repository;

import com.algaworks.AprendizadoSpring.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {

    FotoProduto save(FotoProduto foto);
    void delete(FotoProduto foto);
}
