package com.algaworks.AprendizadoSpring.api.v1.openapi.controller;

import com.algaworks.AprendizadoSpring.api.v1.model.UsuarioModel;
import com.algaworks.AprendizadoSpring.api.v1.model.input.SenhaInput;
import com.algaworks.AprendizadoSpring.api.v1.model.input.UsuarioAtualizaInput;
import com.algaworks.AprendizadoSpring.api.v1.model.input.UsuarioComSenhaInput;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;


public interface UsuarioControllerOpenApi {

    CollectionModel<UsuarioModel> listar();

    UsuarioModel buscar(Long usuarioId);

    UsuarioModel adicionar(UsuarioComSenhaInput usuarioInput);

    UsuarioModel atualizar(Long usuarioId,UsuarioAtualizaInput UsuarioAtualizaInput);

    ResponseEntity<Void> alterarSenha(Long usuarioId, SenhaInput senha);

}
