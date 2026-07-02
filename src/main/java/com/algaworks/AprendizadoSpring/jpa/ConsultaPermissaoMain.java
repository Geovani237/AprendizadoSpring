package com.algaworks.AprendizadoSpring.jpa;

import com.algaworks.AprendizadoSpring.AprendizadoSpringApplication;
import com.algaworks.AprendizadoSpring.domain.model.Permissao;
import com.algaworks.AprendizadoSpring.domain.repository.PermissaoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaPermissaoMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AprendizadoSpringApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PermissaoRepository cadastroPermissao = applicationContext.getBean(PermissaoRepository.class);

        List<Permissao> Permissaos = cadastroPermissao.listar();

        for (Permissao Permissao : Permissaos) {
            System.out.printf("%d - %s - %s%n", Permissao.getId(), Permissao.getNome(), Permissao.getDescricao());
        }
    }
}
