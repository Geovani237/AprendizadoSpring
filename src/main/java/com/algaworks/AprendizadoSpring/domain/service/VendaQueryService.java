package com.algaworks.AprendizadoSpring.domain.service;

import com.algaworks.AprendizadoSpring.domain.filter.VendaDiariaFilter;
import com.algaworks.AprendizadoSpring.domain.model.dto.VendaDiaria;

import java.util.List;

public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias (VendaDiariaFilter filter);
}
