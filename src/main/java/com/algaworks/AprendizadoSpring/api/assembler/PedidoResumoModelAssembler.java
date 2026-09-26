package com.algaworks.AprendizadoSpring.api.assembler;

import com.algaworks.AprendizadoSpring.api.AlgaLinks;
import com.algaworks.AprendizadoSpring.api.controller.EstadoController;
import com.algaworks.AprendizadoSpring.api.controller.PedidoController;
import com.algaworks.AprendizadoSpring.api.controller.RestauranteController;
import com.algaworks.AprendizadoSpring.api.controller.UsuarioController;
import com.algaworks.AprendizadoSpring.api.model.PedidoModel;
import com.algaworks.AprendizadoSpring.api.model.PedidoResumoModel;
import com.algaworks.AprendizadoSpring.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public PedidoResumoModelAssembler() {
        super(PedidoController.class, PedidoResumoModel.class);
    }

    public PedidoResumoModel toModel(Pedido pedido) {
        PedidoResumoModel pedidoResumoModel = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoResumoModel);

        pedidoResumoModel.add(algaLinks.linkToPedidosResumoModel());

        pedidoResumoModel.getRestaurante().add(
                algaLinks.linkToRestaurantes(pedido.getRestaurante().getId()));

        pedidoResumoModel.getCliente().add(algaLinks.linkToCliente(pedido.getCliente().getId()));

         return pedidoResumoModel;
    }

    public List<PedidoResumoModel> toCollectionModel(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(pedido -> toModel(pedido))
                .collect(Collectors.toList());
    }
}
