package com.algaworks.AprendizadoSpring.domain.event;

import com.algaworks.AprendizadoSpring.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
//Quando falamos de Event escrevemos no passado
public class PedidoConfirmadoEvent {

    private Pedido pedido;
}
