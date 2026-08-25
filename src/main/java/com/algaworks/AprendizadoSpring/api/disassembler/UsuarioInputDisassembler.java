package com.algaworks.AprendizadoSpring.api.disassembler;

import com.algaworks.AprendizadoSpring.api.model.input.UsuarioAtualizaInput;
import com.algaworks.AprendizadoSpring.api.model.input.UsuarioAtualizaSenhaInput;
import com.algaworks.AprendizadoSpring.api.model.input.UsuarioCadastroInput;
import com.algaworks.AprendizadoSpring.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario toDomainObject(UsuarioCadastroInput usuarioCadastroInput) {
        return modelMapper.map(usuarioCadastroInput, Usuario.class);
    }

    public void copyToDomainObject(UsuarioAtualizaInput usuarioAtualizaInput, Usuario usuario) {
        modelMapper.map(usuarioAtualizaInput, usuario);
    }

    public void cotyToDomainObjectPassword(UsuarioAtualizaSenhaInput usuarioAtualizaSenhaInput,
        Usuario usuario) {
        modelMapper.map(usuarioAtualizaSenhaInput, usuario);
    }

}
