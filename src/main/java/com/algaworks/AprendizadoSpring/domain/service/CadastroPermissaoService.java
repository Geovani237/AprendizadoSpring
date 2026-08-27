package com.algaworks.AprendizadoSpring.domain.service;

import com.algaworks.AprendizadoSpring.domain.exception.PermissaoNaoEncontradaException;
import com.algaworks.AprendizadoSpring.domain.model.Permissao;
import com.algaworks.AprendizadoSpring.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao buscarOuFalhar(Long permisssaoId) {
        return permissaoRepository.findById(permisssaoId)
                .orElseThrow(() -> new PermissaoNaoEncontradaException(permisssaoId));
    }
}
