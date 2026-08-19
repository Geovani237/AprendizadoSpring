package com.algaworks.AprendizadoSpring.core.jackson;

import com.algaworks.AprendizadoSpring.api.model.mixin.CidadeMixin;
import com.algaworks.AprendizadoSpring.api.model.mixin.CozinhaMixin;
import com.algaworks.AprendizadoSpring.domain.model.Cidade;
import com.algaworks.AprendizadoSpring.domain.model.Cozinha;
import com.algaworks.AprendizadoSpring.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }
}
