package com.algaworks.AprendizadoSpring.api.disassembler;

import com.algaworks.AprendizadoSpring.api.model.input.UsuarioAtualizaInput;
import com.algaworks.AprendizadoSpring.api.model.input.SenhaInput;
import com.algaworks.AprendizadoSpring.api.model.input.UsuarioComSenhaInput;
import com.algaworks.AprendizadoSpring.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario toDomainObject(UsuarioComSenhaInput usuarioComSenhaInput) {
        return modelMapper.map(usuarioComSenhaInput, Usuario.class);
    }

    public void copyToDomainObject(UsuarioAtualizaInput usuarioAtualizaInput, Usuario usuario) {
        modelMapper.map(usuarioAtualizaInput, usuario);
    }

    public void cotyToDomainObjectPassword(SenhaInput senhaInput,
                                           Usuario usuario) {
        modelMapper.map(senhaInput, usuario);
    }

}
