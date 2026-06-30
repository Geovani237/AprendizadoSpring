package com.algaworks.AprendizadoSpring.di.service;

import com.algaworks.AprendizadoSpring.di.modelo.Cliente;
import com.algaworks.AprendizadoSpring.di.notificacao.NivelUrgencia;
import com.algaworks.AprendizadoSpring.di.notificacao.Notificador;
import com.algaworks.AprendizadoSpring.di.notificacao.TipoDoNotificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class AtivacaoClienteService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void ativar(Cliente cliente) {
        cliente.ativar();

        // dizer para o container que o cliente está ativo neste momento
        eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
    }
}
