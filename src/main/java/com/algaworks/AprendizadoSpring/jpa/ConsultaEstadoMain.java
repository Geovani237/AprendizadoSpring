package com.algaworks.AprendizadoSpring.jpa;

import com.algaworks.AprendizadoSpring.AprendizadoSpringApplication;
import com.algaworks.AprendizadoSpring.domain.model.Estado;
import com.algaworks.AprendizadoSpring.domain.repository.EstadoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaEstadoMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AprendizadoSpringApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        EstadoRepository cadastroEstado = applicationContext.getBean(EstadoRepository.class);

        List<Estado> Estados = cadastroEstado.listar();

        for (Estado Estado : Estados) {
            System.out.printf("%d - %s%n", Estado.getId(), Estado.getNome());
        }
    }
}
