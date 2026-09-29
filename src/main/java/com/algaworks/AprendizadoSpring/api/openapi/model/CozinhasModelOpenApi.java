package com.algaworks.AprendizadoSpring.api.openapi.model;

import com.algaworks.AprendizadoSpring.api.model.CidadeModel;
import com.algaworks.AprendizadoSpring.api.model.CozinhaModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import java.util.List;

@ApiModel("CozinhasModel")
@Setter
@Getter
public class CozinhasModelOpenApi {

    private CozinhasModelOpenApi.CozinhasEmbeddedModelOpenApi _embedded;
    private Link _links;
    private PageModelOpenApi page;

    @ApiModel("CidadesEmbeddedModel")
    @Data
    public class CozinhasEmbeddedModelOpenApi {

        private List<CozinhaModel> cozinhas;
    }


}
