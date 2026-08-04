package com.algaworks.AprendizadoSpring.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value = HttpStatus.NOT_FOUND) //, reason = "Entidade não encontrada")// Se essa excessão foi lançada e não tratada, nos queremos que o status Http seja o especificado por nós
public class EntidadeNaoEncontradaException extends ResponseStatusException {

    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public EntidadeNaoEncontradaException(String message) {
        this(HttpStatus.NOT_FOUND, message);
    }
}
