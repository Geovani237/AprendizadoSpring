package com.algaworks.AprendizadoSpring.jpa;

import com.algaworks.AprendizadoSpring.AprendizadoSpringApplication;
import com.algaworks.AprendizadoSpring.domain.model.FormaPagamento;
import com.algaworks.AprendizadoSpring.domain.model.Restaurante;
import com.algaworks.AprendizadoSpring.domain.repository.FormaPagamentoRepository;
import com.algaworks.AprendizadoSpring.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaFormaPagamentoMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AprendizadoSpringApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        FormaPagamentoRepository cadastroFormaPagamento = applicationContext.getBean(FormaPagamentoRepository.class);

        List<FormaPagamento> formaPagamentos = cadastroFormaPagamento.listar();

        for (FormaPagamento formaPagamento : formaPagamentos) {
            System.out.printf("%d - %s%n", formaPagamento.getId(), formaPagamento.getDescricao());
        }
    }
}
