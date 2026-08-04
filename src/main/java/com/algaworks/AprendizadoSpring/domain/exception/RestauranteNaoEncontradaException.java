package com.algaworks.AprendizadoSpring.domain.exception;

public class RestauranteNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public RestauranteNaoEncontradaException(String message) {
        super(message);
    }

    public RestauranteNaoEncontradaException(Long restauranteId) {
        this(String.format("Não existe um cadastro de restaurante com código %d", restauranteId));
    }
}
