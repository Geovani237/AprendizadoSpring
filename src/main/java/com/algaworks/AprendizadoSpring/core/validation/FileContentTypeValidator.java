package com.algaworks.AprendizadoSpring.core.validation;

import org.springframework.http.MediaType;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

    private List<String> mediaType;

    @Override
    public void initialize(FileContentType constraintAnnotation) {
        this.mediaType = List.of(constraintAnnotation.allowed());
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return value == null || this.mediaType.contains(value.getContentType());
    }
}
