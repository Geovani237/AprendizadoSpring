package com.algaworks.AprendizadoSpring.api.controller;

import com.algaworks.AprendizadoSpring.api.assembler.UsuarioModelAssembler;
import com.algaworks.AprendizadoSpring.api.disassembler.UsuarioInputDisassembler;
import com.algaworks.AprendizadoSpring.api.model.UsuarioModel;
import com.algaworks.AprendizadoSpring.api.model.input.UsuarioAtualizaInput;
import com.algaworks.AprendizadoSpring.api.model.input.UsuarioAtualizaSenhaInput;
import com.algaworks.AprendizadoSpring.api.model.input.UsuarioCadastroInput;
import com.algaworks.AprendizadoSpring.domain.exception.NegocioException;
import com.algaworks.AprendizadoSpring.domain.exception.UsuarioNaoEncontradaException;
import com.algaworks.AprendizadoSpring.domain.model.Usuario;
import com.algaworks.AprendizadoSpring.domain.repository.UsuarioRepository;
import com.algaworks.AprendizadoSpring.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroUsuarioService cadastroService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;

    //TODO listar
    @GetMapping
    public List<UsuarioModel> listar() {
        return usuarioModelAssembler.toCollectionModel(usuarioRepository.findAll());
    }

    //TODO buscar
    @GetMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioModel buscar(@PathVariable Long usuarioId) {
        return usuarioModelAssembler.toModel(cadastroService.buscarOuFalhar(usuarioId));
    }

    //TODO adicionar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioCadastroInput usuarioInput) {
        Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
        return usuarioModelAssembler.toModel(cadastroService.salvar(usuario));
    }


    //TODO atualizar
    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioModel atualizar(@PathVariable Long usuarioId,
        @RequestBody @Valid UsuarioAtualizaInput usuarioAtualizaInput) {
        try {
            Usuario usuario = cadastroService.buscarOuFalhar(usuarioId);

            usuarioInputDisassembler.copyToDomainObject(usuarioAtualizaInput, usuario);

            return usuarioModelAssembler.toModel(cadastroService.salvar(usuario));
        } catch (UsuarioNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    //TODO atualizar senha
    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarSenha(@PathVariable Long usuarioId,
        @RequestBody @Valid UsuarioAtualizaSenhaInput senha){
        cadastroService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }
}
