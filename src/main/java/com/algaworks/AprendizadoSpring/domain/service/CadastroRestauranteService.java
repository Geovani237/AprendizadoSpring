package com.algaworks.AprendizadoSpring.domain.service;

import com.algaworks.AprendizadoSpring.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.AprendizadoSpring.domain.exception.RestauranteNaoEncontradaException;
import com.algaworks.AprendizadoSpring.domain.model.Cozinha;
import com.algaworks.AprendizadoSpring.domain.model.Restaurante;
import com.algaworks.AprendizadoSpring.domain.repository.CozinhaRepository;
import com.algaworks.AprendizadoSpring.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

//    private static final String MSG_COZINHA_NAO_ENCONTRADAO = "Não existe cadastro de cozinha com código %d";
//    private static final String MSG_RESTAURANTE_NAO_ENCONTRADAO = "Não existe cadastro de restaurante com código %d";

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

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
