package com.algaworks.AprendizadoSpring.api.v1.controller;

import com.algaworks.AprendizadoSpring.api.v1.AlgaLinks;
import com.algaworks.AprendizadoSpring.api.v1.assembler.ProdutoModelAssembler;
import com.algaworks.AprendizadoSpring.api.v1.disassembler.ProdutoInputDisassembler;
import com.algaworks.AprendizadoSpring.api.v1.model.ProdutoModel;
import com.algaworks.AprendizadoSpring.api.v1.model.input.ProdutoInput;
import com.algaworks.AprendizadoSpring.api.v1.openapi.controller.RestauranteProdutoControllerOpenApi;
import com.algaworks.AprendizadoSpring.domain.exception.NegocioException;
import com.algaworks.AprendizadoSpring.domain.exception.ProdutoNaoEncontradaException;
import com.algaworks.AprendizadoSpring.domain.model.Produto;
import com.algaworks.AprendizadoSpring.domain.model.Restaurante;
import com.algaworks.AprendizadoSpring.domain.repository.ProdutoRepository;
import com.algaworks.AprendizadoSpring.domain.service.CadastroProdutoService;
import com.algaworks.AprendizadoSpring.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;

    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

    @Autowired
    private AlgaLinks algaLinks;

    @Override
    @GetMapping
    public CollectionModel<ProdutoModel> listar(@PathVariable Long restauranteId,
                                                @RequestParam(required = false, defaultValue = "false") Boolean incluirInativos) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        List<Produto> todosProdutos = null;

        if (incluirInativos) {
            todosProdutos = produtoRepository.findTodosByRestaurante(restaurante);
        } else {
            todosProdutos = produtoRepository.findAtivosByRestaurante(restaurante);
        }

        return produtoModelAssembler.toCollectionModel(todosProdutos)
                .add(algaLinks.linkToProdutos(restauranteId));
    }

    @GetMapping(value = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = cadastroProdutoService.buscarOuFalhar(produtoId, restauranteId);
        return produtoModelAssembler.toModel(produto);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel adicionar(@PathVariable Long restauranteId,
            @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        produto.setRestaurante(restaurante);


        return produtoModelAssembler.toModel(cadastroProdutoService.salvar(produto));
    }

    @PutMapping(value = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProdutoModel atualizar(@PathVariable Long restauranteId,
            @PathVariable Long produtoId, @RequestBody @Valid ProdutoInput produtoInput) {
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
