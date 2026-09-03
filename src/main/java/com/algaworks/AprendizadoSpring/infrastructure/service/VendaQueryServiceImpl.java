package com.algaworks.AprendizadoSpring.infrastructure.service;

import com.algaworks.AprendizadoSpring.domain.filter.VendaDiariaFilter;
import com.algaworks.AprendizadoSpring.domain.model.dto.VendaDiaria;
import com.algaworks.AprendizadoSpring.domain.service.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class VendaQueryServiceImpl implements VendaQueryService {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter) {
        return List.of();
    }
}
