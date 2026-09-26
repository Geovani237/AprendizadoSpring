package com.algaworks.AprendizadoSpring.api.assembler;

import com.algaworks.AprendizadoSpring.api.AlgaLinks;
import com.algaworks.AprendizadoSpring.api.controller.CidadeController;
import com.algaworks.AprendizadoSpring.api.controller.UsuarioController;
import com.algaworks.AprendizadoSpring.api.controller.UsuarioGrupoController;
import com.algaworks.AprendizadoSpring.api.model.CidadeModel;
import com.algaworks.AprendizadoSpring.api.model.GrupoModel;
import com.algaworks.AprendizadoSpring.api.model.UsuarioModel;
import com.algaworks.AprendizadoSpring.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioModelAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public UsuarioModelAssembler() {
        super(UsuarioController.class, UsuarioModel.class);
    }

    public UsuarioModel toModel(Usuario usuario) {
        UsuarioModel usuarioModel = createModelWithId(usuario.getId(), usuario);
        modelMapper.map(usuario, usuarioModel);

        usuarioModel.add(
                algaLinks.linkToListarUsuarios("usuarios"));

        usuarioModel.add(
                algaLinks.linkToListarGrupoUsuarios(usuario.getId(), "grupo-usuario"));

        return usuarioModel;
    }

    @Override
    public CollectionModel<UsuarioModel> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities)
                .add(WebMvcLinkBuilder.linkTo(UsuarioController.class).withSelfRel());
    }

}
