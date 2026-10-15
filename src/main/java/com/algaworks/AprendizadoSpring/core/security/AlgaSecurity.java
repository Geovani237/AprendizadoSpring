package com.algaworks.AprendizadoSpring.core.security;

import com.algaworks.AprendizadoSpring.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class AlgaSecurity {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUsuarioId() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();

        return jwt.getClaim("usuario_id");
    }

    public boolean gerenciaRestaurante(Long restuaranteId) {
        if (restuaranteId == null) {
            return false;
        }

        return restauranteRepository.existsResponsavel(restuaranteId, getUsuarioId());
    }
}
