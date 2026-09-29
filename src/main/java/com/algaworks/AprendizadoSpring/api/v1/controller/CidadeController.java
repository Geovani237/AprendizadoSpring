package com.algaworks.AprendizadoSpring.api.v1.controller;

import com.algaworks.AprendizadoSpring.api.ResourceUriHelper;
import com.algaworks.AprendizadoSpring.api.v1.assembler.CidadeModelAssembler;
import com.algaworks.AprendizadoSpring.api.v1.disassembler.CidadeInputDisassembler;
import com.algaworks.AprendizadoSpring.api.v1.model.CidadeModel;
import com.algaworks.AprendizadoSpring.api.v1.model.input.CidadeInput;
import com.algaworks.AprendizadoSpring.api.v1.openapi.controller.CidadeControllerOpenApi;
import com.algaworks.AprendizadoSpring.core.web.AlgaMediaTypes;
import com.algaworks.AprendizadoSpring.domain.exception.EstadoNaoEncontradaException;
import com.algaworks.AprendizadoSpring.domain.exception.NegocioException;
import com.algaworks.AprendizadoSpring.domain.model.Cidade;
import com.algaworks.AprendizadoSpring.domain.repository.CidadeRepository;
import com.algaworks.AprendizadoSpring.domain.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/cidades")
public class CidadeController implements CidadeControllerOpenApi {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @GetMapping(produces = AlgaMediaTypes.V1_APPLICATION_JSON_VALUE)
    public CollectionModel<CidadeModel> listar() {
        List<Cidade> todasCidades = cidadeRepository.findAll();

        return cidadeModelAssembler.toCollectionModel(todasCidades);

    }

    @GetMapping(path = "/{cidadeId}", produces = AlgaMediaTypes.V1_APPLICATION_JSON_VALUE)
    public CidadeModel buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);

        return cidadeModelAssembler.toModel(cidade);
    }


    @PostMapping(produces = AlgaMediaTypes.V1_APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

            cidade = cadastroCidade.salvar(cidade);

            CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidade);

//            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
//                    .path("/{id}")
//                    .buildAndExpand(cidadeModel.getId()).toUri();
//
//            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
//
//            response.setHeader(HttpHeaders.LOCATION, uri.toString());

            ResourceUriHelper.addUriInResponseHeader(cidadeModel.getId());

            return cidadeModel;
        } catch (EstadoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }


    @PutMapping(path = "/{cidadeId}", produces = AlgaMediaTypes.V1_APPLICATION_JSON_VALUE)
    public CidadeModel atualizar(@PathVariable Long cidadeId,
            @RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

            cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);

            cidadeAtual = cadastroCidade.salvar(cidadeAtual);

            return cidadeModelAssembler.toModel(cidadeAtual);
        } catch (EstadoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }


    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(
            @PathVariable Long cidadeId) {
        cadastroCidade.excluir(cidadeId);
    }
}
