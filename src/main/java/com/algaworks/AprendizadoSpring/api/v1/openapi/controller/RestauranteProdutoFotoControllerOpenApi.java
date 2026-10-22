package com.algaworks.AprendizadoSpring.api.v1.openapi.controller;

import com.algaworks.AprendizadoSpring.api.v1.model.FotoProdutoModel;
import com.algaworks.AprendizadoSpring.api.v1.model.input.FotoProdutoInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface RestauranteProdutoFotoControllerOpenApi {

    FotoProdutoModel atualizarFoto(Long restauranteId, Long produtoId, FotoProdutoInput fotoProdutoInput
    ) throws IOException;

    ResponseEntity<Void> excluir(Long restauranteId,Long produtoId);

    FotoProdutoModel buscar(Long restauranteId,Long produtoId);

    ResponseEntity<?> servir(Long restauranteId, Long produtoId, String acceptHeader)
            throws HttpMediaTypeNotAcceptableException;

}