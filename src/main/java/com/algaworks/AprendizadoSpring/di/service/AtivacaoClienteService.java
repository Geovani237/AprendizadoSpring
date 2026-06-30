package com.algaworks.AprendizadoSpring.di.service;

import com.algaworks.AprendizadoSpring.di.modelo.Cliente;
import com.algaworks.AprendizadoSpring.di.notificacao.NivelUrgencia;
import com.algaworks.AprendizadoSpring.di.notificacao.Notificador;
import com.algaworks.AprendizadoSpring.di.notificacao.TipoDoNotificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    @TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
    @Autowired
    private Notificador notificador;

    public void ativar(Cliente cliente) {
        cliente.ativar();

        notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
    }
}
