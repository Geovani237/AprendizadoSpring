package com.algaworks.AprendizadoSpring.di.service;

import com.algaworks.AprendizadoSpring.di.modelo.Cliente;
import com.algaworks.AprendizadoSpring.di.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AtivacaoClienteService {

    @Autowired(required = false)
    private List<Notificador> notificadores;

    public void ativar(Cliente cliente) {
        cliente.ativar();

        for (Notificador notificador : notificadores) {
            notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
        }
    }
}
