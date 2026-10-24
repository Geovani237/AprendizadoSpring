package com.algaworks.AprendizadoSpring.api.v1.openapi.controller;

import com.algaworks.AprendizadoSpring.api.v1.model.PermissaoModel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
public interface PermissaoControllerOpenApi {

    CollectionModel<PermissaoModel> listar();

}