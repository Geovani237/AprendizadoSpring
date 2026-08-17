package com.algaworks.AprendizadoSpring.domain.service;

import com.algaworks.AprendizadoSpring.domain.exception.RestauranteNaoEncontradaException;
import com.algaworks.AprendizadoSpring.domain.model.Cozinha;
import com.algaworks.AprendizadoSpring.domain.model.Restaurante;
import com.algaworks.AprendizadoSpring.domain.repository.CozinhaRepository;
import com.algaworks.AprendizadoSpring.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradaException(restauranteId));
    }
}
