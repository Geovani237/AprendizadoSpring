package com.algaworks.AprendizadoSpring.api.model.mixin;

import com.algaworks.AprendizadoSpring.domain.model.Cozinha;
import com.algaworks.AprendizadoSpring.domain.model.Endereco;
import com.algaworks.AprendizadoSpring.domain.model.FormaPagamento;
import com.algaworks.AprendizadoSpring.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

//Classe mixin são classe onde são colocadas anotações do Jackson de serialização e de desserialização, sem precisar mexer no código fonte da classe original
public abstract class RestauranteMixin {

    // a propriedade allowGetters serve para evitar ou não que seja serializados
    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Cozinha cozinha;

    @JsonIgnore
    private Endereco endereco;

//    @JsonIgnore
    private OffsetDateTime dataCadastro;

//    @JsonIgnore
    private OffsetDateTime dataAtualizacao;

    @JsonIgnore
    private List<FormaPagamento> formasPagamento;

    @JsonIgnore
    private List<Produto> produtos;
}
