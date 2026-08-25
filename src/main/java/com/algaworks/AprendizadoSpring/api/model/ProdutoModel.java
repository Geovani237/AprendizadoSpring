package com.algaworks.AprendizadoSpring.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoModel {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean ativo;
}
