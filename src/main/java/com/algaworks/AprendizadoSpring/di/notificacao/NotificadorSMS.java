package com.algaworks.AprendizadoSpring.di.notificacao;

import com.algaworks.AprendizadoSpring.di.modelo.Cliente;
import org.springframework.stereotype.Component;

@Component
public class NotificadorSMS implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s por SMS através do telefonre %s: %s\n",
                cliente.getNome(), cliente.getTelefone(), mensagem);
    }
}
