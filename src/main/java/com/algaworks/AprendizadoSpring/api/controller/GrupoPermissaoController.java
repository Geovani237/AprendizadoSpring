package com.algaworks.AprendizadoSpring.api.controller;

import com.algaworks.AprendizadoSpring.api.assembler.PermissaoModelAssembler;
import com.algaworks.AprendizadoSpring.api.model.PermissaoModel;
import com.algaworks.AprendizadoSpring.domain.model.Grupo;
import com.algaworks.AprendizadoSpring.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @GetMapping
    public List<PermissaoModel> listar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);

        return permissaoModelAssembler.toCollectionModel(grupo.getPermissoes());
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long permissaoId, @PathVariable Long grupoId) {
        cadastroGrupoService.associarPermissao(permissaoId, grupoId);
    }

    @DeleteMapping ("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long permissaoId, @PathVariable Long grupoId) {
        cadastroGrupoService.desassociarPermissao(permissaoId, grupoId);
    }
}
