package com.algaworks.AprendizadoSpring.di.notificacao;

import com.algaworks.AprendizadoSpring.di.modelo.Cliente;
import org.springframework.stereotype.Component;

public class NotificadorEmail implements Notificador {

    private boolean caixaAlta;
    private String hostServidorSmtp;

    public NotificadorEmail(String hostServidorSmtp) {
        this.hostServidorSmtp = hostServidorSmtp;
        System.out.println("NotificadorEmail");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        if (caixaAlta) {
            mensagem = mensagem.toUpperCase();
        }
        System.out.printf("Notificando %s através do e-mail %s usando SMPT %s: %s\n",
                cliente.getNome(), cliente.getEmail(), this.hostServidorSmtp, mensagem);
    }

    public void setCaixaAlta(boolean caixaAlta) {
        this.caixaAlta = caixaAlta;
    }
}
