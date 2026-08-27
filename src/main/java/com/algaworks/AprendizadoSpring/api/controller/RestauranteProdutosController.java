package com.algaworks.AprendizadoSpring.api.controller;

import com.algaworks.AprendizadoSpring.api.assembler.ProdutoModelAssembler;
import com.algaworks.AprendizadoSpring.api.assembler.RestauranteModelAssembler;
import com.algaworks.AprendizadoSpring.api.disassembler.ProdutoInputDisassembler;
import com.algaworks.AprendizadoSpring.api.disassembler.RestauranteInputDisassembler;
import com.algaworks.AprendizadoSpring.api.model.ProdutoModel;
import com.algaworks.AprendizadoSpring.api.model.RestauranteModel;
import com.algaworks.AprendizadoSpring.api.model.input.ProdutoInput;
import com.algaworks.AprendizadoSpring.api.model.input.RestauranteInput;
import com.algaworks.AprendizadoSpring.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.AprendizadoSpring.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.AprendizadoSpring.domain.exception.NegocioException;
import com.algaworks.AprendizadoSpring.domain.exception.ProdutoNaoEncontradaException;
import com.algaworks.AprendizadoSpring.domain.model.Produto;
import com.algaworks.AprendizadoSpring.domain.model.Restaurante;
import com.algaworks.AprendizadoSpring.domain.repository.ProdutoRepository;
import com.algaworks.AprendizadoSpring.domain.repository.RestauranteRepository;
import com.algaworks.AprendizadoSpring.domain.service.CadastroProdutoService;
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
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;

    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

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
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel adicionar(@PathVariable Long restauranteId,
            @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        produto.setRestaurante(restaurante);


        return produtoModelAssembler.toModel(cadastroProdutoService.salvar(produto));
    }

    //TODO atualizar
    @PutMapping("/{produtoId}")
    public ProdutoModel atualizar(@PathVariable Long restauranteId,
            @RequestBody @Valid ProdutoInput produtoInput, @PathVariable Long produtoId) {
        try {
            Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
            Produto produtoAtual = cadastroProdutoService.buscarOuFalhar(produtoId, restauranteId);

            produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);

            produtoAtual.setRestaurante(restaurante);

            return produtoModelAssembler.toModel(cadastroProdutoService.salvar(produtoAtual));
        } catch (ProdutoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }


    }
}
