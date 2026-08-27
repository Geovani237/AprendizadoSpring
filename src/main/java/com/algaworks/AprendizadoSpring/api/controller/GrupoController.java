package com.algaworks.AprendizadoSpring.api.controller;

import com.algaworks.AprendizadoSpring.api.assembler.GrupoModelAssembler;
import com.algaworks.AprendizadoSpring.api.disassembler.GrupoInputDisassembler;
import com.algaworks.AprendizadoSpring.api.model.GrupoModel;
import com.algaworks.AprendizadoSpring.api.model.input.GrupoInput;
import com.algaworks.AprendizadoSpring.domain.exception.GrupoNaoEncontradaException;
import com.algaworks.AprendizadoSpring.domain.exception.NegocioException;
import com.algaworks.AprendizadoSpring.domain.model.Grupo;
import com.algaworks.AprendizadoSpring.domain.repository.GrupoRepository;
import com.algaworks.AprendizadoSpring.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private CadastroGrupoService cadastroGrupo;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private GrupoInputDisassembler grupoInputDissabler;

    @GetMapping
    public List<GrupoModel> listar() {
        return grupoModelAssembler.toColletionModel(grupoRepository.findAll());
    }

    @GetMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.OK)
    public GrupoModel buscar(@PathVariable Long grupoId) {
        return grupoModelAssembler.toModel(cadastroGrupo.buscarOuFalhar(grupoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel salvar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoInputDissabler.toDomainObject(grupoInput);
        return grupoModelAssembler.toModel(cadastroGrupo.salvar(grupo));
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.OK)
    public GrupoModel atualizar(@PathVariable Long grupoId,
        @RequestBody @Valid GrupoInput grupoInput) {

        try {
            Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

            grupoInputDissabler.copyToDomainObject(grupoInput, grupo);

            return grupoModelAssembler.toModel(grupoRepository.save(grupo));
        } catch (GrupoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        cadastroGrupo.excluir(grupoId);
    }
}
