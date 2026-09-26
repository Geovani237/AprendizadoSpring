package com.algaworks.AprendizadoSpring.api.openapi.controller;

import com.algaworks.AprendizadoSpring.domain.filter.VendaDiariaFilter;
import com.algaworks.AprendizadoSpring.domain.model.dto.VendaDiaria;
import io.swagger.annotations.*;
import com.algaworks.AprendizadoSpring.api.exceptionhandler.Problem;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.List;

@Api(tags = "Estatísticas")
public interface EstatisticasControllerOpenApi {

    @ApiOperation("Consulta estatísticas de vendas diárias")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "restauranteId", value = "ID do restaurante",
                    example = "1", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "dataCriacaoInicio", value = "Data/hora inicial da criação do pedido",
                    example = "2019-12-01T00:00:00Z", dataTypeClass = OffsetDateTime.class),
            @ApiImplicitParam(name = "dataCriacaoFim", value = "Data/hora final da criação do pedido",
                    example = "2019-12-02T23:59:59Z", dataTypeClass = OffsetDateTime.class)
    })
    List<VendaDiaria> consultarVendasDiarias(
            VendaDiariaFilter filtro,

            @ApiParam(value = "Deslocamento de horário a ser considerado na consulta em relação ao UTC",
                    defaultValue = "+00:00")
            String timeOffset);
}