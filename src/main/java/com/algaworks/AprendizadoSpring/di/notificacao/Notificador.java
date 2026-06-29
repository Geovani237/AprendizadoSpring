package com.algaworks.AprendizadoSpring.di.notificacao;

import com.algaworks.AprendizadoSpring.di.modelo.Cliente;

public interface Notificador {
    void notificar(Cliente cliente, String mensagem);
}
