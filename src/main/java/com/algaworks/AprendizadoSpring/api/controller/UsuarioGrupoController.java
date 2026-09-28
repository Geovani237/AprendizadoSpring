package com.algaworks.AprendizadoSpring.api.controller;

import com.algaworks.AprendizadoSpring.api.assembler.GrupoModelAssembler;
import com.algaworks.AprendizadoSpring.api.assembler.UsuarioModelAssembler;
import com.algaworks.AprendizadoSpring.api.model.GrupoModel;
import com.algaworks.AprendizadoSpring.api.model.UsuarioModel;
import com.algaworks.AprendizadoSpring.api.openapi.controller.UsuarioGrupoControllerOpenApi;
import com.algaworks.AprendizadoSpring.domain.model.Usuario;
import com.algaworks.AprendizadoSpring.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

    @Autowired
    private CadastroUsuarioService cadastroUsuario;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Override
    @GetMapping
    public CollectionModel<GrupoModel> listar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);

        return grupoModelAssembler.toCollectionModel(usuario.getGrupos())
                .removeLinks();
    }

    @PutMapping("/{grupoId}")
    public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuario.associar(usuarioId, grupoId);
    }

    @DeleteMapping("/{grupoId}")
    public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuario.desassociar(usuarioId, grupoId);
    }
}
