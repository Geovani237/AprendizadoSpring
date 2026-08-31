package com.algaworks.AprendizadoSpring.core.modelmapper;

import com.algaworks.AprendizadoSpring.api.model.EnderecoModel;
import com.algaworks.AprendizadoSpring.api.model.input.ItemPedidoInput;
import com.algaworks.AprendizadoSpring.domain.model.Endereco;
import com.algaworks.AprendizadoSpring.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        var enderecoToEndececoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);

        enderecoToEndececoModelTypeMap.<String>addMapping(
                enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
                (enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value)
        );

        modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        return modelMapper;
    }
}
