package com.algaworks.AprendizadoSpring;

import com.algaworks.AprendizadoSpring.di.modelo.Cliente;
import com.algaworks.AprendizadoSpring.di.service.AtivacaoClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeuPrimeiroController {

    private AtivacaoClienteService ativacaoClienteService;

    public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
        this.ativacaoClienteService = ativacaoClienteService;
    }

    @GetMapping("/hello")
    @ResponseBody // nos diz que o retorno seja devolvido como resposta
    public String hello() {
        Cliente joao = new Cliente("João", "joao@xyz.com", "199999999");

        ativacaoClienteService.ativar(joao);

        return "Hello";
    }
}
