package com.algaworks.AprendizadoSpring.api.model.mixin;

import com.algaworks.AprendizadoSpring.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class CidadeMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;
}
