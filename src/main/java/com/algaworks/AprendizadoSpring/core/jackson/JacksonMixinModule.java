package com.algaworks.AprendizadoSpring.core.jackson;

import com.algaworks.AprendizadoSpring.api.model.mixin.RestauranteMixin;
import com.algaworks.AprendizadoSpring.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
    }
}
