package com.algaworks.AprendizadoSpring.api.controller;

import com.algaworks.AprendizadoSpring.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.AprendizadoSpring.domain.exception.NegocioException;
import com.algaworks.AprendizadoSpring.domain.model.Cidade;
import com.algaworks.AprendizadoSpring.domain.repository.CidadeRepository;
import com.algaworks.AprendizadoSpring.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @GetMapping
    public List<Cidade> lista() {
        return cidadeRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody Cidade cidade) {
        try {
            return cadastroCidade.salvar(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }


    @GetMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.OK)
    public Cidade buscar(@PathVariable Long cidadeId) {
        return cadastroCidade.buscarOuFalhar(cidadeId);
    }

    @PutMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.OK)
    public Cidade atualizar(@PathVariable Long cidadeId,
                                            @RequestBody Cidade cidade) {
        Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");

        try {
            return cadastroCidade.salvar(cidadeAtual);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cadastroCidade.excluir(cidadeId);
    }
}
