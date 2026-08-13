package com.algaworks.AprendizadoSpring.core.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@AllArgsConstructor
@Getter
public class ValidacaoException extends RuntimeException {

    private BindingResult bindingResult;

    public ValidacaoException(String message) {
        super(message);
    }
}
