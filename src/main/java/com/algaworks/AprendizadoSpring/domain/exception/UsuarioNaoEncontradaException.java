package com.algaworks.AprendizadoSpring.domain.exception;

public class UsuarioNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public UsuarioNaoEncontradaException(String message) {
        super(message);
    }

    public UsuarioNaoEncontradaException(Long usuarioId) {
        this(String.format("Não existe um cadastro de usuário com código %d", usuarioId));
    }
}
