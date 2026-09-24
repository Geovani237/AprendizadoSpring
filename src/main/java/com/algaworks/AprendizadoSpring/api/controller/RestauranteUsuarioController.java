package com.algaworks.AprendizadoSpring.api.controller;

import com.algaworks.AprendizadoSpring.api.assembler.UsuarioModelAssembler;
import com.algaworks.AprendizadoSpring.api.model.UsuarioModel;
import com.algaworks.AprendizadoSpring.api.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import com.algaworks.AprendizadoSpring.domain.model.Restaurante;
import com.algaworks.AprendizadoSpring.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioController implements RestauranteUsuarioResponsavelControllerOpenApi {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @GetMapping()
    public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        return usuarioModelAssembler.toCollectionModel(restaurante.getUsuarios())
                .removeLinks()
                .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteUsuarioController.class).listar(restauranteId)).withSelfRel());
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desasscociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestauranteService.desassociarUsuario(restauranteId, usuarioId);
    }

    @PutMapping(value = "/{usuarioId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestauranteService.associarUsuario(restauranteId, usuarioId);
    }
}
