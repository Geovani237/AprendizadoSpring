package com.algaworks.AprendizadoSpring.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioComSenhaInput{

    @ApiModelProperty(example = "João da Silva", required = true)
    @NotBlank
    private String nome;

    @ApiModelProperty(example = "joao.ger@algafood.com.br", required = true)
    @NotBlank
    @Email
    private String email;

    @ApiModelProperty(example = "123", required = true)
    @NotBlank
    private String senha;
}
