package com.algaworks.AprendizadoSpring.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteModel extends RepresentationModel<RestauranteModel> {

//    @JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
    @Schema(example = "1")
    private Long id;

//    @JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
    @Schema(example = "Thai Gourmet")
    private String nome;

//    @JsonView(RestauranteView.Resumo.class)
    @Schema(example = "12.00")
    private BigDecimal taxaFrete;

//    @JsonView(RestauranteView.Resumo.class)
    private CozinhaModel cozinha;

    private Boolean ativo;
    private Boolean aberto;
    private EnderecoModel endereco;
}
