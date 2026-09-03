package com.algaworks.AprendizadoSpring.api.controller;

import com.algaworks.AprendizadoSpring.domain.filter.VendaDiariaFilter;
import com.algaworks.AprendizadoSpring.domain.model.dto.VendaDiaria;
import com.algaworks.AprendizadoSpring.domain.service.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {

    @Autowired
    private VendaQueryService vendaQueryService;

    @GetMapping("/vendas-diarias")
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro) {
        return vendaQueryService.consultarVendasDiarias(filtro);
    }
}
