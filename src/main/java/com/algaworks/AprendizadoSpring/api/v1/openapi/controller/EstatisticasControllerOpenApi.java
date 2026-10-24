package com.algaworks.AprendizadoSpring.api.v1.openapi.controller;

import com.algaworks.AprendizadoSpring.domain.filter.VendaDiariaFilter;
import com.algaworks.AprendizadoSpring.domain.model.dto.VendaDiaria;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@SecurityRequirement(name = "security_auth")
public interface EstatisticasControllerOpenApi {

    List<VendaDiaria> consultarVendasDiarias(
            VendaDiariaFilter filtro,String timeOffset);

}