package com.algaworks.AprendizadoSpring.core.email;

import com.algaworks.AprendizadoSpring.domain.service.EnvioEmailService;
import com.algaworks.AprendizadoSpring.infrastructure.service.email.FakeEnvioEmailService;
import com.algaworks.AprendizadoSpring.infrastructure.service.email.SmtpEnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EnvioEmailService envioEmailService() {
        // Acho melhor usar switch aqui do que if/else if
        switch (emailProperties.getImpl()) {
            case FAKE:
                return new FakeEnvioEmailService();
            case SMTP:
                return new SmtpEnvioEmailService();
            default:
                return null;
        }
    }

}