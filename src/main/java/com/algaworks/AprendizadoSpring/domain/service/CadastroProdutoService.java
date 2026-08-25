package com.algaworks.AprendizadoSpring.domain.service;

import com.algaworks.AprendizadoSpring.domain.exception.ProdutoNaoEncontradaException;
import com.algaworks.AprendizadoSpring.domain.model.Produto;
import com.algaworks.AprendizadoSpring.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto buscarOuFalhar(Long produtoId) {
        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradaException(produtoId));
    }
}
