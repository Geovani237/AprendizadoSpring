package com.algaworks.AprendizadoSpring.api.openapi.model;

import com.algaworks.AprendizadoSpring.api.model.CidadeModel;
import com.algaworks.AprendizadoSpring.domain.model.Cidade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Link;

import java.util.List;

@ApiModel("CidadesModel")
@Data
public class CidadesModelOpenApi {

    private CidadeEmbeddedModelOpenApi _embedded;
    private Link _links;

    @ApiModel("CidadesEmbeddedModel")
    @Data
    public class CidadeEmbeddedModelOpenApi {

        private List<CidadeModel> cidades;
    }
}
