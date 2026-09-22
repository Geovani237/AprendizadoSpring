package com.algaworks.AprendizadoSpring.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoIdInput {

    @ApiModelProperty(example = "1")
    private Long id;
}
