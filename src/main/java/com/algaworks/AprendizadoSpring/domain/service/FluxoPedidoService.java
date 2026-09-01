package com.algaworks.AprendizadoSpring.domain.service;

import com.algaworks.AprendizadoSpring.domain.exception.NegocioException;
import com.algaworks.AprendizadoSpring.domain.model.Pedido;
import com.algaworks.AprendizadoSpring.domain.model.StatusPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class FluxoPedidoService {

    private final String MSG_STATUS_PEDIDO = "Status do pedido %d não pode ser alterado de %s para %s";

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Transactional
    public void confirmar(Long pedidoId) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);

        verificarStatusPedido(pedido, StatusPedido.CRIADO, StatusPedido.CONFIRMADO);
    }

    @Transactional
    public void entrega(Long pedidoId) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);

        verificarStatusPedido(pedido, StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE);
    }

    @Transactional
    public void cancelar(Long pedidoId) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);

        verificarStatusPedido(pedido, StatusPedido.CRIADO, StatusPedido.CANCELADO);
    }

    private void verificarStatusPedido(Pedido pedido, StatusPedido statusAtual, StatusPedido statusDesejado) {
        if (!pedido.getStatus().equals(statusAtual)) {
            throw new NegocioException(String.format(
                    MSG_STATUS_PEDIDO,
                    pedido.getId(), pedido.getStatus().getDescricao(), statusDesejado.getDescricao()
            ));
        }

        pedido.setStatus(statusDesejado);
        pedido.setDataConfirmacao(OffsetDateTime.now());
    }

}
