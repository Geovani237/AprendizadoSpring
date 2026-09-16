package com.algaworks.AprendizadoSpring.domain.event;

import com.algaworks.AprendizadoSpring.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoCanceladoEvent {

    private Pedido pedido;
}
