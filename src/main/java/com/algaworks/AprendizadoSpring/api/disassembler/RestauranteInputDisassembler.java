package com.algaworks.AprendizadoSpring.api.disassembler;

import com.algaworks.AprendizadoSpring.api.model.input.RestauranteInput;
import com.algaworks.AprendizadoSpring.domain.model.Cozinha;
import com.algaworks.AprendizadoSpring.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {
        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteInput.getCozinha().getId());;

        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
        restaurante.setCozinha(cozinha);

        return restaurante;
    }
}
