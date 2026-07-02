package com.algaworks.AprendizadoSpring.jpa;

import com.algaworks.AprendizadoSpring.AprendizadoSpringApplication;
import com.algaworks.AprendizadoSpring.domain.model.Cidade;
import com.algaworks.AprendizadoSpring.domain.repository.CidadeRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaCidadeMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AprendizadoSpringApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CidadeRepository cadastroCidade = applicationContext.getBean(CidadeRepository.class);

        List<Cidade> Cidades = cadastroCidade.listar();

        for (Cidade Cidade : Cidades) {
            System.out.printf("%d - %s - %s%n", Cidade.getId(), Cidade.getNome(), Cidade.getEstado().getNome());
        }
    }
}
