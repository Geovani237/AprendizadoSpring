package com.algaworks.AprendizadoSpring.api.v1.controller;

import com.algaworks.AprendizadoSpring.api.v1.assembler.CozinhaModelAssembler;
import com.algaworks.AprendizadoSpring.api.v1.disassembler.CozinhaInputDisassembler;
import com.algaworks.AprendizadoSpring.api.v1.model.CozinhaModel;
import com.algaworks.AprendizadoSpring.api.v1.model.input.CozinhaInput;
import com.algaworks.AprendizadoSpring.api.v1.openapi.controller.CozinhaControllerOpenApi;
import com.algaworks.AprendizadoSpring.core.security.CheckSecurity;
import com.algaworks.AprendizadoSpring.domain.model.Cozinha;
import com.algaworks.AprendizadoSpring.domain.repository.CozinhaRepository;
import com.algaworks.AprendizadoSpring.domain.service.CadastroCozinhaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

//@Controller
//@ResponseBody //Indica que as resposta do metodos desse controlador deve ser enviada como resposta da requisição HTTP
@RestController // Essa anotação contem a anotação de controller e responsebody
@RequestMapping(value = "/v1/cozinhas") //, produces = MediaType.APPLICATION_JSON_VALUE) //As requisições que chegam na nossa api temos devem ser mapeadas, para que esse controlador fique responsavel por certas requisições
public class CozinhaController implements CozinhaControllerOpenApi {

    private static final Logger logger = LoggerFactory.getLogger(CozinhaController.class);

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @Autowired
    private CozinhaInputDisassembler cozinhaInputDisassembler;

    @Autowired
    private PagedResourcesAssembler pagedResourcesAssembler;

//    @PreAuthorize("isAuthenticated()")
    @CheckSecurity.Cozinhas.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) //Requisições com Get chegam até esse metodo
    public PagedModel<CozinhaModel> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

//        List<CozinhaModel> cozinhasModel = cozinhaModelAssembler.toCollectionModel(cozinhasPage.getContent());
//
//        Page<CozinhaModel> cozinhasModelPage = new PageImpl<>(cozinhasModel, pageable, cozinhasPage.getTotalPages());
        PagedModel<CozinhaModel> cozinhasPagedModel = pagedResourcesAssembler
                .toModel(cozinhasPage, cozinhaModelAssembler);

        return cozinhasPagedModel;
    }


//    @PreAuthorize("isAuthenticated()")
    @CheckSecurity.Cozinhas.PodeConsultar
    @GetMapping(value = "/{cozinhaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CozinhaModel buscar(@PathVariable Long cozinhaId) {
        return cozinhaModelAssembler.toModel(cadastroCozinha.buscarOuFalhar(cozinhaId));

//        Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);
//
//        if (cozinha.isPresent()) {
//            return ResponseEntity.ok(cozinha.get());
//        }
//        return ResponseEntity.notFound().build();
    }

//    @PreAuthorize("hasAuthority('EDITAR_COZINHAS')")
//    @PodeEditarCozinhas
    @CheckSecurity.Cozinhas.PodeEditar
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
        return cozinhaModelAssembler.toModel(cadastroCozinha.salvar(cozinha));
    }

//    @PreAuthorize("hasAuthority('EDITAR_COZINHAS')")
//    @PodeEditarCozinhas
    @CheckSecurity.Cozinhas.PodeEditar
    @PutMapping(value = "/{cozinhaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CozinhaModel atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {

        Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);

        cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);

//        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        return cozinhaModelAssembler.toModel(cadastroCozinha.salvar(cozinhaAtual));

//        Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);
//
//        if (cozinhaAtual.isPresent()) {
//            BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
//
//            Cozinha cozinhaSalva = cadastroCozinha.salvar(cozinhaAtual.get());
//            return ResponseEntity.ok().body(cozinhaSalva);
//        }
//        return ResponseEntity.notFound().build();
    }


//    @PreAuthorize("hasAuthority('EDITAR_COZINHAS')")
//    @PodeEditarCozinhas
    @CheckSecurity.Cozinhas.PodeEditar
    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> remover(@PathVariable Long cozinhaId) {
        cadastroCozinha.excluir(cozinhaId);
        return ResponseEntity.noContent().build();
    }
}
