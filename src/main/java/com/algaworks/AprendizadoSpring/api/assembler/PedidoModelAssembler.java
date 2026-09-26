package com.algaworks.AprendizadoSpring.api.assembler;

import com.algaworks.AprendizadoSpring.api.AlgaLinks;
import com.algaworks.AprendizadoSpring.api.controller.*;
import com.algaworks.AprendizadoSpring.api.model.PedidoModel;
import com.algaworks.AprendizadoSpring.api.model.RestauranteResumoModel;
import com.algaworks.AprendizadoSpring.domain.model.FormaPagamento;
import com.algaworks.AprendizadoSpring.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public PedidoModelAssembler() {
        super(PedidoController.class, PedidoModel.class);
    }

    public PedidoModel toModel(Pedido pedido) {
        PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoModel);


        pedidoModel.add(algaLinks.linkToPedidos());


//        pedidoModel.add(WebMvcLinkBuilder.linkTo(PedidoController.class).withRel("pedidos"));

        pedidoModel.getRestaurante().add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(RestauranteController.class)
                        .buscar(pedido.getRestaurante().getId())).withSelfRel());

        pedidoModel.getCliente().add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(UsuarioController.class)
                        .buscar(pedido.getCliente().getId())).withSelfRel());

        pedidoModel.getFormaPagamento().add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(FormaPagamentoController.class)
                        .buscar(pedido.getFormaPagamento().getId(), null)).withSelfRel());

//        pedidoModel.getFormaPagamento().add(WebMvcLinkBuilder.linkTo(FormaPagamentoController.class).withSelfRel());

        pedidoModel.getEnderecoEntrega().getCidade().add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(CidadeController.class)
                        .buscar(pedido.getEnderecoEntrega().getCidade().getId())).withSelfRel());

        pedidoModel.getItens().forEach(itemPedidoModel -> {
            itemPedidoModel.add(WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(RestauranteProdutosController.class)
                            .buscar(pedidoModel.getRestaurante().getId(), itemPedidoModel.getProdutoId())).withRel("produto"));
        });


         return pedidoModel;
    }

    public List<PedidoModel> toCollectionModel(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(pedido -> toModel(pedido))
                .collect(Collectors.toList());
    }
}
