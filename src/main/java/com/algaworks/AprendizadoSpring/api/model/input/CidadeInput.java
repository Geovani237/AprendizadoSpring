package com.algaworks.AprendizadoSpring.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeInput {

    @ApiModelProperty(example = "Uberlândia")
    @NotBlank
    private String nome;

    @NotNull
    @Valid
    private EstadoIdInput estado;
}
