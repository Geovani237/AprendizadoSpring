package com.algaworks.AprendizadoSpring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeuPrimeiroController {

    @GetMapping("/hello")
    @ResponseBody // nos diz que o retorno seja devolvido como resposta
    public String hello() {
        return "Hello!";
    }
}
