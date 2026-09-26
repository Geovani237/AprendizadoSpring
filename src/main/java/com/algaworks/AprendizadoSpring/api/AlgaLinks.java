package com.algaworks.AprendizadoSpring.api;

import com.algaworks.AprendizadoSpring.api.controller.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class AlgaLinks {

    public static final  TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
            new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM));


    public Link linkToPedidos() {
        TemplateVariables filterVariables = new TemplateVariables(
                new TemplateVariable("clienteId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("restauranteId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("dataCriacaoInicio", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("dataCriacaoFim", TemplateVariable.VariableType.REQUEST_PARAM));

        String pedidosUrl = WebMvcLinkBuilder.linkTo(PedidoController.class).toUri().toString();

        return Link.of(UriTemplate.of(pedidosUrl, PAGINACAO_VARIABLES.concat(filterVariables)), "pedidos");
    }

    public Link linkToRestaurantes(Long restauranteId, String rel) {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(RestauranteController.class)
                        .buscar(restauranteId)).withRel(rel);
    }
    public Link linkToRestaurantes(Long restauranteId) {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(RestauranteController.class)
                        .buscar(restauranteId)).withSelfRel();
    }

    public Link linkToCliente(Long clienteId) {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(UsuarioController.class)
                        .buscar(clienteId)).withSelfRel();
    }

    public Link linkToFormaPagamento(Long formaPagamentoId) {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(FormaPagamentoController.class)
                        .buscar(formaPagamentoId, null)).withSelfRel();
    }

    public Link linkToCidade(Long cidadeId) {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(CidadeController.class)
                        .buscar(cidadeId)).withSelfRel();
    }

    public Link linkToItens(Long restauranteId, Long produtoId) {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(RestauranteProdutosController.class)
                        .buscar(restauranteId, produtoId)).withRel("produto");
    }

    public Link linkToPedidosResumoModel() {
        return WebMvcLinkBuilder.linkTo(PedidoController.class).withRel("pedidos");
    }

    public Link linkToListarCidade(String rel) {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(CidadeController.class)
                        .listar()).withRel(rel);
    }

    public Link linkToEstado(Long estadoId) {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(EstadoController.class)
                        .buscar(estadoId)).withSelfRel();
    }

    public Link linkToEstado(String rel) {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(EstadoController.class)
                        .listar()).withRel(rel);
    }

    public Link linkToListarUsuarios(String rel) {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(UsuarioController.class)
                        .listar()).withRel(rel);
    }

    public Link linkToListarGrupoUsuarios(Long usuarioId, String rel) {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(UsuarioGrupoController.class)
                        .listar(usuarioId)).withRel(rel);
    }

    public Link linkToListarRestaurantes(Long restauranteId) {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(RestauranteUsuarioController.class)
                        .listar(restauranteId)).withSelfRel();
    }

    public Link linkToCozinhas(String rel) {
        return WebMvcLinkBuilder.linkTo(CozinhaController.class).withRel(rel);
    }
}
