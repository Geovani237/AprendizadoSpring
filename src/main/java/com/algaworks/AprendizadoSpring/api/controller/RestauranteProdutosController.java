package com.algaworks.AprendizadoSpring.api.controller;

import com.algaworks.AprendizadoSpring.api.assembler.ProdutoModelAssembler;
import com.algaworks.AprendizadoSpring.api.assembler.RestauranteModelAssembler;
import com.algaworks.AprendizadoSpring.api.disassembler.RestauranteInputDisassembler;
import com.algaworks.AprendizadoSpring.api.model.ProdutoModel;
import com.algaworks.AprendizadoSpring.api.model.RestauranteModel;
import com.algaworks.AprendizadoSpring.api.model.input.RestauranteInput;
import com.algaworks.AprendizadoSpring.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.AprendizadoSpring.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.AprendizadoSpring.domain.exception.NegocioException;
import com.algaworks.AprendizadoSpring.domain.model.Produto;
import com.algaworks.AprendizadoSpring.domain.model.Restaurante;
import com.algaworks.AprendizadoSpring.domain.repository.RestauranteRepository;
import com.algaworks.AprendizadoSpring.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutosController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;

    //TODO listar
    @GetMapping
    public List<ProdutoModel> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        return produtoModelAssembler.toCollectionModel(restaurante.getProdutos());
    }

    //TODO buscar
    @GetMapping("/{produtoId}")
    public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = cadastroRestaurante.buscarProduto(produtoId, restauranteId);

        return produtoModelAssembler.toModel(produto);
    }

    //TODO adicionar
    //TODO atualizar

}
