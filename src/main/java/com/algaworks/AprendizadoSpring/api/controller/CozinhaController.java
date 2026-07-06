package com.algaworks.AprendizadoSpring.api.controller;

import com.algaworks.AprendizadoSpring.domain.model.Cozinha;
import com.algaworks.AprendizadoSpring.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller
//@ResponseBody //Indica que as resposta do metodos desse controlador deve ser enviada como resposta da requisição HTTP
@RestController // Essa anotação contem a anotação de controller e responsebody
@RequestMapping(value = "/cozinhas") //, produces = MediaType.APPLICATION_JSON_VALUE) //As requisições que chegam na nossa api temos devem ser mapeadas, para que esse controlador fique responsavel por certas requisições
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) //Requisições com Get chegam até esse metodo
    public List<Cozinha> lista1() {
        System.out.println("LISTAR 1");
        return cozinhaRepository.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public List<Cozinha> lista2() {
        System.out.println("LISTAR 2");
        return cozinhaRepository.listar();
    }
}
