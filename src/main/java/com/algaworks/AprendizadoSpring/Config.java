package com.algaworks.AprendizadoSpring;

import com.algaworks.AprendizadoSpring.di.notificacao.NotificadorEmail;
import com.algaworks.AprendizadoSpring.di.service.AtivacaoClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class Config {

    @Bean
    public NotificadorEmail notificadorEmail() {
        NotificadorEmail notificador = new NotificadorEmail("smtp.algamail.com.br");
        notificador.setCaixaAlta(true);

        return notificador;
    }

    @Bean
    public AtivacaoClienteService ativacaoClienteService() {
        return new AtivacaoClienteService(notificadorEmail());
    }
}
