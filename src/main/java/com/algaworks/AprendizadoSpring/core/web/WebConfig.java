package com.algaworks.AprendizadoSpring.core.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("*");
                .allowedMethods("*");
//                .maxAge(10);
    }

    /*
    CORS (Cross-Origin Resource Sharing, ou Compartilhamento de Recursos entre Origens)
    é um mecanismo de segurança usado pelos navegadores para controlar como um site em um
    domínio (origem) pode solicitar recursos de um servidor em um domínio diferente.

     */
}
