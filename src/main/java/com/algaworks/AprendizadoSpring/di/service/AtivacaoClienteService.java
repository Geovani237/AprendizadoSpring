package com.algaworks.AprendizadoSpring.di.service;

import com.algaworks.AprendizadoSpring.di.modelo.Cliente;
import com.algaworks.AprendizadoSpring.di.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    //Ponto de injeção no Atributo (Usado pela simplicidade)
    @Autowired
    private Notificador notificador;

    //Ponto de injeção no Construtor(Ideal que se use no construtor)
//    @Autowired
//    public AtivacaoClienteService(Notificador notificador) {
//        this.notificador = notificador;
//    }

//    public AtivacaoClienteService(String qualquer) {
//
//    }

    public void ativar(Cliente cliente) {
        cliente.ativar();

        notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
    }

    //Ponto de injeção no Setter(Menos usado)
//    @Autowired
//    public void setNotificador(Notificador notificador) {
//        this.notificador = notificador;
//    }

}
