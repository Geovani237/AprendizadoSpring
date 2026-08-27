package com.algaworks.AprendizadoSpring.api.controller;

import com.algaworks.AprendizadoSpring.api.assembler.GrupoModelAssembler;
import com.algaworks.AprendizadoSpring.api.assembler.UsuarioModelAssembler;
import com.algaworks.AprendizadoSpring.api.model.GrupoModel;
import com.algaworks.AprendizadoSpring.api.model.UsuarioModel;
import com.algaworks.AprendizadoSpring.domain.model.Usuario;
import com.algaworks.AprendizadoSpring.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    @Autowired
    private CadastroUsuarioService cadastroUsuario;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @GetMapping
    public List<GrupoModel> lista(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);

        return grupoModelAssembler.toColletionModel(usuario.getGrupos());
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
