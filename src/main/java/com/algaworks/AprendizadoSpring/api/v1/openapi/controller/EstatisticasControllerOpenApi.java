package com.algaworks.AprendizadoSpring.api.v1.openapi.controller;

import com.algaworks.AprendizadoSpring.api.v1.controller.EstatisticasController;
import com.algaworks.AprendizadoSpring.domain.filter.VendaDiariaFilter;
import com.algaworks.AprendizadoSpring.domain.model.dto.VendaDiaria;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EstatisticasControllerOpenApi {

    List<VendaDiaria> consultarVendasDiarias(
            VendaDiariaFilter filtro,String timeOffset);

}