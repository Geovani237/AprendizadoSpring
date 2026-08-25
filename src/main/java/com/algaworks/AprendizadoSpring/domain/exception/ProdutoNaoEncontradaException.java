package com.algaworks.AprendizadoSpring.domain.exception;

public class ProdutoNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public ProdutoNaoEncontradaException(String message) {
        super(message);
    }

    public ProdutoNaoEncontradaException(Long produtoId) {
        this(String.format("Não existe um cadastro de produto com código %d", produtoId));
    }
}
