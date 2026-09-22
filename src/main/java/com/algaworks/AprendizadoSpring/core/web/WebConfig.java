package com.algaworks.AprendizadoSpring.core.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("*");
                .allowedMethods("*");
//                .maxAge(10);
    }

    @Bean
    public Filter shallowEtagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }

    /*
    CORS (Cross-Origin Resource Sharing, ou Compartilhamento de Recursos entre Origens)
    é um mecanismo de segurança usado pelos navegadores para controlar como um site em um
    domínio (origem) pode solicitar recursos de um servidor em um domínio diferente.

     */
}
