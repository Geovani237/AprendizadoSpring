package com.algaworks.AprendizadoSpring.api.v2.controller;

import com.algaworks.AprendizadoSpring.api.v1.assembler.CozinhaModelAssembler;
import com.algaworks.AprendizadoSpring.api.v1.disassembler.CozinhaInputDisassembler;
import com.algaworks.AprendizadoSpring.api.v1.model.CozinhaModel;
import com.algaworks.AprendizadoSpring.api.v1.model.input.CozinhaInput;
import com.algaworks.AprendizadoSpring.api.v1.openapi.controller.CozinhaControllerOpenApi;
import com.algaworks.AprendizadoSpring.api.v2.assembler.CozinhaModelAssemblerV2;
import com.algaworks.AprendizadoSpring.api.v2.disassembler.CozinhaInputDisassemblerV2;
import com.algaworks.AprendizadoSpring.api.v2.model.CozinhaModelV2;
import com.algaworks.AprendizadoSpring.api.v2.model.input.CozinhaInputV2;
import com.algaworks.AprendizadoSpring.api.v2.openapi.controller.CozinhaControllerV2OpenApi;
import com.algaworks.AprendizadoSpring.domain.model.Cozinha;
import com.algaworks.AprendizadoSpring.domain.repository.CozinhaRepository;
import com.algaworks.AprendizadoSpring.domain.service.CadastroCozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@Controller
//@ResponseBody //Indica que as resposta do metodos desse controlador deve ser enviada como resposta da requisição HTTP
@RestController // Essa anotação contem a anotação de controller e responsebody
@RequestMapping(value = "/v2/cozinhas") //, produces = MediaType.APPLICATION_JSON_VALUE) //As requisições que chegam na nossa api temos devem ser mapeadas, para que esse controlador fique responsavel por certas requisições
public class CozinhaControllerV2 implements CozinhaControllerV2OpenApi {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private CozinhaModelAssemblerV2 cozinhaModelAssembler;

    @Autowired
    private CozinhaInputDisassemblerV2 cozinhaInputDisassembler;

    @Autowired
    private PagedResourcesAssembler pagedResourcesAssembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedModel<CozinhaModelV2> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

        PagedModel<CozinhaModelV2> cozinhasPagedModel = pagedResourcesAssembler
                .toModel(cozinhasPage, cozinhaModelAssembler);

        return cozinhasPagedModel;
    }



    @GetMapping(value = "/{cozinhaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CozinhaModelV2 buscar(@PathVariable Long cozinhaId) {
        return cozinhaModelAssembler.toModel(cadastroCozinha.buscarOuFalhar(cozinhaId));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModelV2 adicionar(@RequestBody @Valid CozinhaInputV2 cozinhaInput) {
        Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
        return cozinhaModelAssembler.toModel(cadastroCozinha.salvar(cozinha));
    }

    @PutMapping(value = "/{cozinhaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CozinhaModelV2 atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInputV2 cozinhaInput) {

        Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);

        cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);

        return cozinhaModelAssembler.toModel(cadastroCozinha.salvar(cozinhaAtual));

    }

}
