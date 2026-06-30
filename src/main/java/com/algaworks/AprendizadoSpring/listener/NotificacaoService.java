package com.algaworks.AprendizadoSpring.listener;

import com.algaworks.AprendizadoSpring.di.notificacao.NivelUrgencia;
import com.algaworks.AprendizadoSpring.di.notificacao.Notificador;
import com.algaworks.AprendizadoSpring.di.notificacao.TipoDoNotificador;
import com.algaworks.AprendizadoSpring.di.service.ClienteAtivadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoService {

    @TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
    @Autowired
    private Notificador notificador;

    @EventListener
    public void clienteAtivadoListener(ClienteAtivadoEvent event) {
        notificador.notificar(event.getCliente(), "Seu cadastro no sistema está ativo!");
    }
}
