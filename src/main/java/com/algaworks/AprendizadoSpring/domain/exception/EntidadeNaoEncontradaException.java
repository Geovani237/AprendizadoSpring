package com.algaworks.AprendizadoSpring.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)// Se essa excessão foi lançada e não tratada, nos queremos que o status Http seja o especificado por nós
public abstract class EntidadeNaoEncontradaException extends NegocioException {

    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
