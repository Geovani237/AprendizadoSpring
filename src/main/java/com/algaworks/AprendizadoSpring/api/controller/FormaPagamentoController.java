package com.algaworks.AprendizadoSpring.api.controller;


import com.algaworks.AprendizadoSpring.api.assembler.FormaPagamentoModelAssembler;
import com.algaworks.AprendizadoSpring.api.disassembler.FormaPagamentoInputDisassembler;
import com.algaworks.AprendizadoSpring.api.model.FormaPagamentoModel;
import com.algaworks.AprendizadoSpring.api.model.input.FormaPagamentoInput;
import com.algaworks.AprendizadoSpring.domain.exception.FormaPagamentoNaoEncontradaException;
import com.algaworks.AprendizadoSpring.domain.exception.NegocioException;
import com.algaworks.AprendizadoSpring.domain.model.FormaPagamento;
import com.algaworks.AprendizadoSpring.domain.repository.FormaPagamentoRepository;
import com.algaworks.AprendizadoSpring.domain.service.CadastroFormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/formas-pagamentos")
public class FormaPagamentoController {

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamento;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @Autowired
    private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

    @GetMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoId, ServletWebRequest request) {

        //Deep ETag
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
        String eTag = "0";
        OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getUltimaAtualizacao();

        if (dataUltimaAtualizacao != null) {
            eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
        }
        if (request.checkNotModified(eTag)) {
            return  null;
        }
        // Fim Deep ETag

        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
        FormaPagamentoModel formaPagamentoModel = formaPagamentoModelAssembler.toModel(formaPagamento);;
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(formaPagamentoModel);
    }

    @GetMapping
    public ResponseEntity<List<FormaPagamentoModel>> listar(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getUltimaAtualizacao();

        if (dataUltimaAtualizacao != null) {
            eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        List<FormaPagamento> todasFormasPagamentos = formaPagamentoRepository.findAll();

        List<FormaPagamentoModel> formasPagamentosModel = formaPagamentoModelAssembler.toColletionModel(todasFormasPagamentos);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .eTag(eTag)
                .body(formasPagamentosModel);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        try {
            FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);
            return formaPagamentoModelAssembler.toModel(cadastroFormaPagamento.salvar(formaPagamento));
        } catch (FormaPagamentoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId,
                                         @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        try {
            FormaPagamento formaPagamentoAtual = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);

            formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);

            return formaPagamentoModelAssembler.toModel(cadastroFormaPagamento.salvar(formaPagamentoAtual));
        } catch (FormaPagamentoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover (@PathVariable Long formaPagamentoId) {
        cadastroFormaPagamento.excluir(formaPagamentoId);
    }
}
