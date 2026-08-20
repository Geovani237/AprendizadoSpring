package com.algaworks.AprendizadoSpring.api.model;

import com.algaworks.AprendizadoSpring.domain.model.Cidade;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoModel {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private CidadeResumoModel cidade;
}
